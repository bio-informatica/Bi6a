package graphExample;

/*This vertex implements  an algorithm 3.1 for Maximal Independent Set
  described in
  "Self Stabilizing Algorithm for Minimal Dominating Sets
  and Maximal Independent Sets"
*/
import java.lang.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

public class  MISVertex extends Vertex implements Cloneable{

    protected boolean c=false;
    public boolean isInMIS(){return c;}
    public void addToMIS(){c=true;}
    public void removeFromMIS(){c=false;}
    public Object clone() throws CloneNotSupportedException {return super.clone();}
    public boolean agree(){
	if (!isInMIS()) {
	    Iterator e=neighbors.iterator();
	    while(e.hasNext())
		if (((MISVertex)e.next()).isInMIS()) return true;
	    return false;
	}
 	Iterator e=neighbors.iterator();
	while(e.hasNext())
	     if (((MISVertex)e.next()).isInMIS()) return false;
	return(true);
    }
    public void move(int info){
	if (isInMIS()){
	    Iterator e=neighbors.iterator();
	    while(e.hasNext())
		if (((MISVertex)e.next()).isInMIS()) {
		    removeFromMIS();
		    return;}
	    
	}
	else{
	    Iterator e=neighbors.iterator();
	    while(e.hasNext())
		if (((MISVertex)e.next()).isInMIS()) return;
	    addToMIS();
	}
    }
    public void randomState(Random r)
     {c=r.nextBoolean();} 
    public void setState(boolean b){c=b;} 
    public boolean getState(){return c;} 
    public void printState(){
	if (c) 
	System.out.println("Vertex " + getName() + "belongs to MIS");
	else
	System.out.println("Vertex " + getName() + "does not belong to MIS");
	    } 
    public String getStateInfo(){
	String s=new String("c : ");
	s=s.concat(String.valueOf(c));
	return s;
    } 
    
    public void paintState(Graphics graphics){
	if (isInMIS()) graphics.setColor(Color.green);
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
    }
}
