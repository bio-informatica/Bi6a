package graphExample;

/*This implements  an algorithm 4.1 for Minimal Dominating Set,
  described in
  "Self Stabilizing Algorithm for Minimal Dominating Sets
  and Maximal Independent Sets"
*/
import java.lang.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

public class  MDSVertex extends Vertex implements Cloneable{
    protected MDSVertex unique=null;
    protected boolean c=false;
    public boolean isInMDS(){return c;}
    public void addToMDS(){c=true;}
    public void removeFromMDS(){c=false;}
    public Object clone() throws CloneNotSupportedException {return super.clone();}

    public boolean clauseP1(){
	if (isInMDS() && unique!=null) return true;
	return false;
    }
    public void actionP1(){
	unique=null;
    }

    public boolean clauseP2(){
	if (!isInMDS()){
	    int nMDS=0;
	    MDSVertex u=null;
	    Iterator e=neighbors.iterator();
	    while(e.hasNext()){
		MDSVertex v=((MDSVertex)e.next());
		if (v.isInMDS()) {nMDS++; u=v;} 
	    }
	    if (nMDS==1&&unique!=u) return true; 
	}
	return false;
    }
    public void actionP2(){
	Iterator e=neighbors.iterator();
	while(e.hasNext()){
	    MDSVertex v=((MDSVertex)e.next());
	    if (v.isInMDS()) {unique=v; return;} 
	}
    }


    public boolean clauseP3(){
	if (unique==null) return false;
	if (!isInMDS()){
	    int nMDS=0;
	    MDSVertex u=null;
	    Iterator e=neighbors.iterator();
	    while(e.hasNext()){
		MDSVertex v=((MDSVertex)e.next());
		if (v.isInMDS()) {nMDS++; u=v;} 
	    }
	    if (nMDS>1) return true; 
	}
	return false;
    }
    public void actionP3(){
	unique=null;
    }

    public boolean clauseM1(){
	if (!isInMDS()) {
	    Iterator e=neighbors.iterator();
	    while(e.hasNext())
		if (((MDSVertex)e.next()).isInMDS()) return false;
	    return true;
	}
	return false;
    }
    public void actionM1(){
	addToMDS();
    }
    public boolean clauseM2(){
	if (isInMDS()) {
	    Iterator e=neighbors.iterator();
	    boolean help=false;
	    while(e.hasNext()){
		MDSVertex v=((MDSVertex)e.next());
		if (v.unique==this) return false;
		help=help||(v.isInMDS());
	    }
	    if (help) return true;
	}
	return false;
    }

    public void actionM2(){
	removeFromMDS();
    }
    public boolean agree(){
	if (clauseP1()) return false; 
	if (clauseP2()) return false; 
	if (clauseP3()) return false; 
	if (clauseM1()) return false; 
	if (clauseM2()) return false; 
	return  true;
    }

    public void move(int info){
	if (clauseP1()) actionP1();
	if (clauseP2()) actionP2();
	if (clauseP3()) actionP3();
	if (clauseM1()) actionM1();
	if (clauseM2()) actionM2();
    }

    public void randomState(Random r){
	c=r.nextBoolean();
	if (deg()==0) unique=null;
	else{
	    if (r.nextBoolean()) unique=null;
	    else{
		int rnd=r.nextInt(deg());
		Iterator it=neighbors.iterator();
		for(int j=0; j<rnd; j++)
		    it.next();
		unique=(MDSVertex)it.next();
	    }
	}
    }

    public void printState(){
	if (c) 
	System.out.println("Vertex " + getName() + "belongs to MDS");
	else
	System.out.println("Vertex " + getName() + "does not belong to MDS");
	System.out.print("its unique MDS member is :");
	if (unique!=null) System.out.print(unique.getName());
	else System.out.print("null");
    } 
    
    public String getStateInfo(){
	String s=new String("c (x): ");
	s=s.concat(String.valueOf(c));
	s=s.concat("\n").concat("unique (i):");
	if (unique!=null) s=s.concat(unique.getName());
	else s=s.concat("null");
	return s;
    } 
    
    public void paintState(Graphics graphics){
	if (isInMDS()) graphics.setColor(Color.green);
	else  graphics.setColor(Color.black);
	graphics.fillOval(getXCoordinate()-getRadius(),
			  getYCoordinate()-getRadius(),
			  2*getRadius(),2*getRadius());
	graphics.fillOval(getXCoordinate()-getRadius(),getYCoordinate()-getRadius(),2*getRadius(),2*getRadius());
	if (!agree()) {
	    graphics.setColor(Color.red);
	    for (int i=0; i<3; i++) 
		graphics.drawOval(getXCoordinate()-getRadius()-2-i,
				  getYCoordinate()-getRadius()-2-i,4+2*getRadius()+2*i,4+2*getRadius()+2*i);
	}
	if (unique!=null){
	    graphics.setColor(Color.blue);
	    Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setStroke(new BasicStroke(2.5f)); 
	    g2d.draw(new Line2D.Double(getXCoordinate(),
				       getYCoordinate(),
				       unique.getXCoordinate()-2*(unique.getXCoordinate()-getXCoordinate())/3,
				       unique.getYCoordinate()-2*(unique.getYCoordinate()-getYCoordinate())/3));
	}
    }
}
