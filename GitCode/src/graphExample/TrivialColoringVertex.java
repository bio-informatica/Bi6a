package graphExample;

import java.lang.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

public class  TrivialColoringVertex  extends ColoringVertex implements Cloneable{

    public void move(int info){
	ArrayList usedColors = new ArrayList();
	ArrayList freeColors = new ArrayList();
	Iterator e=neighbors.iterator();
	while(e.hasNext()){
	    Integer c=new Integer(((TrivialColoringVertex)e.next()).color);
	    usedColors.add(c);}
	for (int i=0; i<deg()+1; i++) freeColors.add(new Integer(i));
	freeColors.removeAll(usedColors);
	Collections.shuffle(freeColors);
	color=((Integer)((Iterator)freeColors.iterator()).next()).intValue();
	if (info>=Vertex.textInfo2){
	System.out.print("move: Vertex ");
	System.out.println(getName() +  " to color " + color); }
    }

}
