package graphExample;

import java.lang.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

public abstract class  ColoringVertex extends Vertex implements Cloneable{
    public abstract void move(int info);
    protected int color=0;
    protected static final int MINCOLOR=0;

    public int getColor(){return color;}
    /** returns actual number of used colors in a whole graph <i>g</i>
	assuming that vertices extends ColoringVertex */
    public static int colorsUsed(Graph g){
	Iterator it=g.iterator();
	HashSet usedColors = new HashSet();
	while(it.hasNext()) {
	    usedColors.add(new Integer(((ColoringVertex)it.next()).getColor()));
	}
	return usedColors.size();
    }
    public Object clone() throws CloneNotSupportedException {return super.clone();}
    public boolean agree(){
	boolean help=true;
	Iterator e=neighbors.iterator();
	while(help && e.hasNext()){
	    Integer c=new Integer(((ColoringVertex)e.next()).color);
	    help=(c.intValue() != color);
	}
	return(help);
    }
    public void randomState(Random r)
     {color=r.nextInt(deg()+1);} 
    public void setState(int i){color=i;} 
    public int getState(){return color;} 
    public void printState()
    {System.out.println("Vertex " + getName() + " Color : " + color);} 
    public String getStateInfo(){
	String s=new String("Color : ");
	s=s.concat(String.valueOf(color));
	return s;
    } 
    
    public void paintState(Graphics graphics){
	switch (color) {
	case 0: graphics.setColor(Color.black); break;
	case 1: graphics.setColor(Color.blue); break;
	case 2: graphics.setColor(Color.cyan); break;
	case 3: graphics.setColor(Color.yellow); break;
	case 4: graphics.setColor(Color.green); break;
	case 5: graphics.setColor(Color.white); break;
	case 6: graphics.setColor(Color.lightGray); break;
	case 7: graphics.setColor(Color.magenta); break;
	case 8: graphics.setColor(Color.orange); break;
	case 9: graphics.setColor(Color.pink); break;
	case 10: graphics.setColor(Color.darkGray); break;
	case 11: graphics.setColor(Color.gray); break;
	case 12: graphics.setColor(Color.red); break;
	default: graphics.setColor(new Color((color*10)%256,(color*5)%256,(color*7)%256));    
	}   

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
