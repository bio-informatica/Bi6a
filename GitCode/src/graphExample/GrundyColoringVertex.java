package graphExample;

import java.lang.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

public class  GrundyColoringVertex  extends ColoringVertex implements Cloneable{
    public boolean agree(){
	HashSet usedColors = new HashSet();
	boolean help=true;
	Iterator e=neighbors.iterator();
	while(help && e.hasNext()){
	    Integer c=new Integer(((GrundyColoringVertex)e.next()).color);
	    usedColors.add(c);
	    help=(c.intValue() != color);
	}

	if (help) {
	    int i=MINCOLOR;
	    while (i<color && usedColors.contains(new Integer(i))) i++;
	    return (i==color);
	}
	else return (false);}

    public void move(int info){
	HashSet usedColors = new HashSet();
	Iterator e=neighbors.iterator();
	while(e.hasNext()){
	    Integer c=new Integer(((GrundyColoringVertex)e.next()).color);
	    usedColors.add(c);}
	color=0;
	while (usedColors.contains(new Integer(color))) color++;
	if (info>=Vertex.textInfo2){
	System.out.print("move: Vertex ");
	System.out.println(getName() +  " to color " + color); }
    }

}
