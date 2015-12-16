package graphExample;

import java.util.*;
import java.lang.*;
import java.awt.Graphics;
import java.awt.Color;
/**
   This class has been designed to test self-stabilizing algorithms.
   In a model of self-stabilization each vertex has its state.
   Having this state and information about states of its neighbors 
   vertex can determine({@link Vertex#agree}) if it should change its state(is enabled) or not.
   In first case vertex makes a move(change its state {@link Vertex#move}), in second stays without changing state.
   
   We consider a set of vertices connected in some way with edges.
   Such a structure we call a ({@link Graph}). 
   To show this structure to the user we have to place vertices somehow. 
   Each vertex has its coordinates in a virtual Panel. 
   Dimension of this rectangular panel is given by points (0,0) (Graph.MAXX, Graph.MAXY).
   Of course when program runs we have to adjust this coordinates to screen Panel dimensions ({@link Graph#setFactors}).
   */
public abstract class Vertex implements Cloneable{
    private int xCoordinate;
    private int yCoordinate;
    /** Vertex is painted as a circle with radius <i>radius</i> in pixels.
	@see Vertex#paint
	@see Vertex#setRadius
	@see Vertex#getRadius
	@see Graph#setRadius
     */
    private int radius;
    /**
       This factor allows to adjust screen Vertex <i>x</i> coordinate .
       @see Vertex
     */
    public float xFactor=1;
    /**
       This factor allows to adjust screen Vertex <i>y</i> coordinate .
       @see Vertex
     */
    public float yFactor=1;
        /**
       Sets the x coordinate 
       @param x coordinate in screen pixels
       @see Vertex
     */
    public  void setXCoordinate(int x){xCoordinate=(int)(x/xFactor);}
    /**   Sets the y coordinate 
	  @param y coordinate in screen pixels
	  @see Vertex */
    public  void setYCoordinate(int y){yCoordinate=(int)(y/yFactor);}
    /**   Sets vertex  coordinate a
	  @param x  X coordinates in screen pixels
	  @param y  Y coordinates in screen pixels
	  @see Vertex */
    public  void setCoordinates(int x, int y){setXCoordinate(x);setYCoordinate(y);}
    /** Gets the x coordinate 
	@return x coordinate in screen pixels
	@see Vertex */
    public  int getXCoordinate(){return (int)(xCoordinate*xFactor);}
    /**   Gets the y coordinate 
	  @return y coordinate in screen pixels
	  @see Vertex */
    public  int getYCoordinate(){return (int)(yCoordinate*yFactor);}
     /**   Sets radius in pixels. 
	  @param radius length of radius in screen pixels
	  @see Vertex */
    public  void setRadius(int radius){this.radius=radius;}
     /**   Gets radius in pixels. 
	  @return radius length of radius in screen pixels
	  @see Vertex */
    public  int getRadius(){return radius;}
     /**   Sets vertex factors 
	  @param x  X factor
	  @param y  Y factor
	  @see Vertex */
    public void setFactors(float x, float y){xFactor=x; yFactor=y;}
    /** Count a distance between given point (x,y) and Vertex coordinates (getXCoordinate(),getYCoordinate)
	in screen pixels.
	@return distance between given point (x,y) and Vertex coordinates
    */
    public double distance(int x, int y)
    {return Math.sqrt((x-getXCoordinate())*(x-getXCoordinate())+(y-getYCoordinate())*(y-getYCoordinate()));}
    /** Name of a vertex.
	Each vertex name should be unique in the graph 
	@see Vertex#print
	@see Vertex#getName
	@see Vertex#setName
    */ 
    private String name;
    /** All neighbors of a vertex in a graph*/ 
    protected  HashSet neighbors;
    static final int graphInfo1=-1;
    static final int noInfo=0;
    static final int textInfo1=1;
    static final int textInfo2=2;
    static final int textInfo3=3;
    public Vertex(){radius=9;neighbors=new HashSet();}    
    public Vertex(String name){
	this();
	setName(name);}    
    public Vertex(String name, int radius){
	this();
	this.radius=radius;}
    /** This method works properly only with vertices without edges.
     It is used when constructing a Graph*/
    public Object clone()
	throws CloneNotSupportedException{
	Vertex v = (Vertex)super.clone();
	if (name!=null) v.name=new String(name);
	v.neighbors=new HashSet();
	//if (neighbors!=null) v.neighbors=(HashSet)neighbors.clone();
	return v;}
    
    /** returns a String describing Vertex
	@see VertexInfoDialog
    */
    public String getInfo(){
	String s=new String("This is vertex ");
	s=s.concat(getName());
	s=s.concat(new String());
	s=s.concat("\nNeighbors: ");
	Iterator e=neighbors.iterator();
	while(e.hasNext()){
	    s=s.concat(((Vertex)e.next()).getName());
	    s=s.concat(" ");}
	s=s.concat("\n");
	s=s.concat(getStateInfo());
	return s;
    }
    
    /**
       Print vertex info.
       @see Graph#print
     */
    public void print(){
	Iterator e=neighbors.iterator();
	System.out.print(getName() + ": ");
	while(e.hasNext())
	    System.out.print(((Vertex)e.next()).getName() + " ");}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public boolean addEdge(Vertex u){
	if (neighbors.add(u)) return true;
	else return false;}
    /** Return vertex degree in a graph
	@return vertex degree in a graph 
	@see Graph#delta
    */
    public int deg(){return neighbors.size();}
    /**
       Iterator over the neighbors of a vertex.
       @return returns iterator over the neighbors of a vertex.
     */
    public Iterator iterator(){return  neighbors.iterator();}  
    /**
       This method should paint a vertex according to its state,
       however in Vertex class it just paint circles with <i>radius</i>  in red.
       and should be rather overridden in subclasses.
       @see Vertex#setRadius
       @see Graph#paintStates
     */
    public void paintState(Graphics graphics){
	graphics.setColor(Color.black);
	graphics.fillOval(getXCoordinate()-getRadius(),
			  getYCoordinate()-getRadius(),
			  2*getRadius(),2*getRadius());
	if (!agree()) {
	    graphics.setColor(Color.red);
	    for (int i=0; i<3; i++) 
		graphics.drawOval(getXCoordinate()-getRadius()-2-i,
				  getYCoordinate()-getRadius()-2-i,4+2*getRadius()+2*i,4+2*getRadius()+2*i);
     }}
    /**
       This method should determine if vertex state agrees to rules of a specified algorithm
       @return true means that vertex agree, false other way vertex is available.
     */
    public abstract boolean agree();
    /**
       This method should make a move assuming that vertex is enabled.
     */
    public abstract void move(int info);
    /**
       This method should random a state of  a  vertex.
     */
    public abstract void randomState(Random r);
    /**
       This method should print a state of  a  vertex.
     */
    public abstract void printState();
    /**
       This method should get  a state info of  a  vertex.
       @return String describing a vertex state.
       @see VertexInfoDialog
     */
    public abstract String getStateInfo();
}
 
