package graphExample;

import java.lang.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;


public class  EdgeColoringVertex  extends Vertex implements Cloneable{
    List s;
    private int maxColor=0;
    
    public EdgeColoringVertex(){
	super();
	s = Collections.synchronizedList(new ArrayList());
    }

    public boolean clauseR0(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());		
	    if (u!=null)
		if (u.maxColor<=it.previousIndex()) return false;
	}
	return true;
    }

    public void actionR0(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());		
	    if (u!=null)
		if (u.maxColor<=it.previousIndex()) 
		    s.set(it.previousIndex(),null);
	}
    }


    public boolean clauseR1(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    ListIterator it2=s.listIterator(it.nextIndex());
	    it2.next();
	    while(it2.hasNext()){
		if(s.get(it.nextIndex())!=null)
		    if(s.get(it.nextIndex()).equals(s.get(it2.nextIndex())))
			return(false);
		it2.next();}    
	    it.next();}
	return true;}


    public void actionR1(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    boolean duplicate=false;
	    ListIterator it2=s.listIterator(it.nextIndex());
	    it2.next();
	    while(it2.hasNext()){
		if(s.get(it.nextIndex())!=null)
		   if(s.get(it.nextIndex()).equals(s.get(it2.nextIndex()))){
		       duplicate=true;
		       s.set(it2.nextIndex(),null);}
		   it2.next();}
	    if (duplicate==true) s.set(it.nextIndex(),null);
	    it.next();
	}}

    public boolean clauseR2(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    Vertex u=(Vertex)(it.next());
	    if (u!=null){
		ListIterator 
		    it2=((EdgeColoringVertex)u).s.listIterator();
		while(it2.hasNext()){
		    Vertex v=(Vertex)it2.next();
		    if (this.equals(v)){
			if (it.previousIndex()!=it2.previousIndex())
			    return(false);}}}}
	return true;}

    public void actionR2(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    boolean consistent=true;
	    Vertex u=(Vertex)(it.next());
	    if (u!=null){
		ListIterator 
		    it2=((EdgeColoringVertex)u).s.listIterator();
		while(it2.hasNext()){
		    Vertex v=(Vertex)it2.next();
		    if (this.equals(v)){
			if (it.previousIndex()!=it2.previousIndex())
			    consistent=false;}}}
	    if (!consistent) {
		ListIterator it3=s.listIterator(it.previousIndex());
		while(it3.hasNext()){
		    it3.next();
		    if(s.get(it.previousIndex())!=null)
			if(s.get(it.previousIndex()).equals
			   (s.get(it3.previousIndex())))
			    s.set(it3.previousIndex(),null);}
		s.set(it.previousIndex(),null);}}}

    public boolean clauseR3(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());
	    if (u!=null) 
		if(u.maxColor>it.previousIndex()){
		    if ((this!=u.s.get(it.previousIndex()))&&
			(u.s.get(it.previousIndex())!=null))
			return false;}}
	return true; }

    public void actionR3(){
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());
	    if (u!=null) 
		if(u.maxColor>it.previousIndex()){
		    if ((this!=u.s.get(it.previousIndex()))&&
			(u.s.get(it.previousIndex())!=null))
			s.set(it.previousIndex(),null);}}}

    public boolean clauseR4(){
	Iterator it=neighbors.iterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());
	    ListIterator it2=u.s.listIterator();
	    while(it2.hasNext()){
		it2.next();
		if (this==u.s.get(it2.previousIndex()))
		    if(maxColor>it2.previousIndex())
			if (s.get(it2.previousIndex())==null) {
			    ListIterator it3=u.s.listIterator();
			    boolean help=true;
			    while (it3.hasNext()&&help) 
				if (this==it3.next() && 
				    it2.previousIndex()!=it3.previousIndex()) 
				    help=false;
			    if (help) return false;}}}
	return true; 
    }

    public void actionR4(){
	Iterator it=neighbors.iterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());
	    ListIterator it2=u.s.listIterator();
	    while(it2.hasNext()){
		it2.next();
		if (this==u.s.get(it2.previousIndex()))
		    if(maxColor>it2.previousIndex())
			if (s.get(it2.previousIndex())==null){
			    ListIterator it3=u.s.listIterator();
			    boolean help=true;
			    while (it3.hasNext()&&help) 
				if (this==it3.next() && 
				    it2.previousIndex()!=it3.previousIndex()) 
				    help=false;
			    if (help) s.set(it2.previousIndex(),u);}}}
    }



    public boolean clauseR5(){
	Iterator it=neighbors.iterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());
	    ListIterator it2=u.s.listIterator();
	    boolean colorless=true;
	    while(colorless && it2.hasNext()){
		it2.next();
		colorless=(u.s.get(it2.previousIndex())!=this);
	    }
	    if (colorless) {
		it2=s.listIterator();
		while(colorless && it2.hasNext()){
		    it2.next();
		    colorless=(s.get(it2.previousIndex())!=u);}
	    }
	    if (colorless){
		boolean freeColor=false;
		it2=u.s.listIterator();
		while((!freeColor) && it2.hasNext()) {
		    it2.next();
		    if (maxColor>it2.previousIndex())
			freeColor=((s.get(it2.previousIndex())==null)&& 
				   (u.s.get(it2.previousIndex())==null));
		}
		int freeColorNumber=it2.previousIndex();
		if (freeColor) {
		    boolean noInvitation=true;
		    Iterator it3=neighbors.iterator();
		    while(noInvitation&&it3.hasNext()){
			EdgeColoringVertex w=(EdgeColoringVertex)(it3.next());
			if (w.maxColor>freeColorNumber) 
			    noInvitation=w.s.get(freeColorNumber)!=this;
		    }
		    if (noInvitation) 
			return false;
		}
	    }
	}
	return true; 
    }

    public void actionR5(){
	Iterator it=neighbors.iterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());
	    ListIterator it2=u.s.listIterator();
	    boolean colorless=true;
	    while(colorless && it2.hasNext()){
		it2.next();
		colorless=(u.s.get(it2.previousIndex())!=this);
	    }
	    if (colorless) {
		it2=s.listIterator();
		while(colorless && it2.hasNext()){
		    it2.next();
		    colorless=(s.get(it2.previousIndex())!=u);}
	    }
	    if (colorless){
		boolean freeColor=false;
		it2=u.s.listIterator();
		while((!freeColor) && it2.hasNext()) {
		    it2.next();
		    if (maxColor>it2.previousIndex())
			freeColor=((s.get(it2.previousIndex())==null)&& 
				   (u.s.get(it2.previousIndex())==null));
		}
		int freeColorNumber=it2.previousIndex();
		if (freeColor) {
		    boolean noInvitation=true;
		    Iterator it3=neighbors.iterator();
		    while(noInvitation&&it3.hasNext()){
			EdgeColoringVertex w=(EdgeColoringVertex)(it3.next());
			if (w.maxColor>freeColorNumber) 
			    noInvitation=w.s.get(freeColorNumber)!=this;
		    }
		    if (noInvitation) 
			s.set(freeColorNumber,u);
		}}}}
    
    public boolean agree(){
	if (!clauseR0()) return false;
	if (!clauseR1()) return false;
	if (!clauseR2()) return false;
	if (!clauseR3()) return false;
	if (!clauseR4()) return false;
	if (!clauseR5()) return false;
	return true;}
 
	   
    public void move(int info){
	if (!clauseR0()) {actionR0(); return;}
	if (!clauseR1()) {actionR1(); return;}
	if (!clauseR2()) {actionR2(); return;}
	if (!clauseR3()) {actionR3(); return;}
	if (!clauseR4()) {actionR4(); return;}
	if (!clauseR5()) {actionR5(); return;}
    }

    /*maximum degree of neighborhood*/
    public int maxNDeg(){
	int help=0;
	Iterator it=neighbors.iterator();
	while(it.hasNext()){
	    int nd=((Vertex)it.next()).deg();
	    if (nd>help) help=nd;
	}
	return help;
    }
    
    public void randomState(Random r){
	maxColor=deg()+maxNDeg()-1;
	s= Collections.synchronizedList(new ArrayList()); 
	s.clear();
	for(int i=0;i<maxColor;i++){
	    int rnd=r.nextInt(2);
	    if (rnd==1) s.add(null);
	    else {
		int rnd2=r.nextInt(deg());
		Iterator it=neighbors.iterator();
		for(int j=0; j<rnd2; j++)
		    it.next();
		s.add(it.next());
	    }
	}
    }
    public void printState(){
	for (int i=0;i<maxColor;i++)
	    {
		if (s.get(i)==null) 
		    System.out.print(i + " : null\n");
		else 
		System.out.print(i + " : " + ((Vertex)s.get(i)).getName() + "\n");
	    }
	System.out.println();
	}

    public void paintState(Graphics graphics){
	graphics.setColor(Color.black);
	graphics.fillOval(getXCoordinate()-getRadius(),
			  getYCoordinate()-getRadius(),
			  2*getRadius(),2*getRadius());
	if (!agree()) {
	    graphics.setColor(Color.red);
	    for (int i=0; i<3; i++) 
		graphics.drawOval(getXCoordinate()-getRadius()-2-i,
				  getYCoordinate()-getRadius()-2-i,
				  4+2*getRadius()+2*i,4+2*getRadius()+2*i);}
	ListIterator it=s.listIterator();
	while(it.hasNext()){
	    EdgeColoringVertex u=(EdgeColoringVertex)(it.next());
	    if (u!=null){
		int color=it.previousIndex()+1;
		switch (color) {
		case 1: graphics.setColor(Color.blue); break;
		case 2: graphics.setColor(Color.cyan); break;
		case 3: graphics.setColor(Color.yellow); break;
		case 4: graphics.setColor(Color.green); break;
		case 5: graphics.setColor(Color.white); break;
		case 6: graphics.setColor(Color.red); break;
		case 7: graphics.setColor(Color.magenta); break;
		case 8: graphics.setColor(Color.orange); break;
		case 9: graphics.setColor(Color.pink); break;
		case 10: graphics.setColor(Color.darkGray); break;
		case 11: graphics.setColor(Color.gray); break;
		case 12: graphics.setColor(Color.red); break;
		default: graphics.setColor(new Color((color*10)%256,(color*5)%256,
						     (color*7)%256));}
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setStroke(new BasicStroke(2.5f)); 
		for (int i=-1;i<2;i++)
		    g2d.draw(new Line2D.Double(getXCoordinate(),
				      getYCoordinate(),
				      u.getXCoordinate()-(u.getXCoordinate()-getXCoordinate())/2,
				      u.getYCoordinate()-(u.getYCoordinate()-getYCoordinate())/2));
	    }
	}   
    }
    public String getStateInfo(){
	String str=new String("D=");
	str=str.concat(String.valueOf(maxNDeg()));
	str=str.concat("\n").concat(String.valueOf(maxColor)).concat("\n");
	for(int i=0;i<maxColor;i++){
	    if (s.get(i)==null) {
	    str=str.concat(String.valueOf(i));
	    str=str.concat(": null\n");
	    }
	    else{
	    str=str.concat(String.valueOf(i)).concat(" -> ");
	    str=str.concat(((Vertex)(s.get(i))).getName());
	    str=str.concat("\n");
	    }
	}	   
	return str;
    }
}
