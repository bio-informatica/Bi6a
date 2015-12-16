package graphExample;

import java.io.*;
import java.util.*;
import java.awt.Graphics;

/**This represents a mathematical object <i>graph</i>
   as lists of neighbors.
   Graph is a pair (<i>V</i>,<i>E</i>), where <i>V</i>
   is a set of vertices and  <i>V</i> is a set of edges.
   Each edge <i>e</i> is a pair of vertices 
   <i>e</i> = (<i>v<sub><small>1</small></sub> , v<sub><small>2</small></sub></i>) 
 */
public class Graph{
    /** Vertices in a graph */
    private ArrayList vertices;
    /** This Random object is used in all random operation in the class*/
    private Random r=new Random();
    /** Width of virtual Panel used to place vertices
	@see Vertex#getXCoordinate
	@see Graph#setFactors
     */
    public static final int MAXX=10000;
    /** Height of virtual Panel used to place vertices
	@see Vertex#getYCoordinate
	@see Graph#setFactors
     */
    public static final int MAXY=10000;
    /** A number of moves after initialization or last randomStates()
	@see Graph#randomStates
	@see Vertex#move
    */
    public  int moves;
    /** Creates an empty graph without vertices*/
    public Graph(){moves=0; vertices=new ArrayList();}
    /** Creates a tree with <i> n </i> vertices*/
    public Graph(Vertex v, int n){
	this();
	for(int i=0; i<n; i++) {
	    try{
		Vertex u = (Vertex)v.clone();
		addVertex(u, Integer.toString(i), 1); }
	    catch(CloneNotSupportedException e){System.out.println("Cloning not supported");} 
	}}
    /** creates a new graph with the same structure as <i>g</i>, but with vertices of the same type as v */
    public Graph(Graph g, Vertex v){
	this();
	Iterator itg=g.iterator();
	String name;
	itg=g.iterator();
	//create the same number of vertices as in graph g
	while (itg.hasNext()) {
	    name=((Vertex)itg.next()).getName();
	try{
	    Vertex u = (Vertex)v.clone();
	    addVertex(u, name);}
	catch(CloneNotSupportedException e){System.out.println("Cloning not supported");} 
	}
	
	itg=g.iterator();	
	Iterator it=iterator();    
	Vertex vg,ug;
	//for each vertex vg in g and corresponding vertex v in this graph
	while (itg.hasNext()) {
	    v=(Vertex)(it.next());
	    vg=(Vertex)(itg.next());
	    Iterator itg2=vg.iterator();    
	    // for each neighbor ug of vg in
	    while (itg2.hasNext()) {
		ug=(Vertex)(itg2.next());
		//find corresponding vertex in this graph and connect it with v
		Iterator it2=iterator();
		boolean help=true;
		while (it2.hasNext()&&help) {
		    Vertex u=(Vertex)it2.next();
		    if (u.getName()==ug.getName()) if(addEdge(u,v)) help=false;
		}
	    }
	}
    }
    /** Creates a random graph with <i> n </i> vertices, where each edge appears with probability <i>p</i>*/
    public Graph(Vertex v, int n, float p){
	this();
	for(int i=0; i<n; i++) {
	    try{
		Vertex u = (Vertex)v.clone();
		addVertex(u, Integer.toString(i), p); }
	    catch(CloneNotSupportedException e){System.out.println("Cloning not supported");} 
	}}

    /** Creates a random bipartite graph with <i> n=n1+n2 </i> vertices, 
	where each edge appears with probability <i>p</i>*/
    public Graph(Vertex v, int n1, int n2, float p){
	this();
	for(int i=0; i<n1; i++) {
	    try{
		Vertex u = (Vertex)v.clone();
		addVertex(u, Integer.toString(i)); }
	  
	    catch(CloneNotSupportedException e){System.out.println("Cloning not supported");} 
	}
	ArrayList verticesCopy=new ArrayList();
	verticesCopy.addAll(vertices);
	for(int i=0; i<n2; i++) {
	    try{
		Vertex u = (Vertex)v.clone();
		addVertex(u, Integer.toString(i+n1));
		Iterator it=verticesCopy.iterator();
		for(int j=0; j<verticesCopy.size(); j++){
		    if (r.nextDouble()<=p) addEdge(u,(Vertex)it.next());
		    else it.next();
		}		
	    }
	    catch(CloneNotSupportedException e){System.out.println("Cloning not supported");} 
	}
    }
    /*
      there is a problem with cloning a graph()
      *    public Object clone() throws CloneNotSupportedException{
      *	Graph g=(Graph)super.clone();
      *	 if (vertices!=null) g.vertices=(ArrayList)vertices.clone();
      *	 
      *	 if (r!=null) g.r=new Random();
      *	 return g;
	 } */
    
    /** Add a vertex <i>v</i> to a graph as isolated one. */
    public void addVertex(Vertex v, String name){
	v.setName(name);
	vertices.add(v);}
    /** Add a vertex <i>v</i> to a graph with degree  <i>d</i>. If <i>d</i> is greater then actual number 
     of vertices then the new vertex will be connected with each one.*/
    public void addVertex(Vertex v, String name, int d){
	ArrayList verticesCopy=new ArrayList(vertices);
	v.setName(name);
	vertices.add(v);
	Collections.shuffle(verticesCopy);
	Iterator e=verticesCopy.iterator();
	if (d>verticesCopy.size()) d=verticesCopy.size();
	for(int i=0; i<d; i++) addEdge(v,(Vertex)e.next());	    
    }
    /** Add a vertex <i>v</i> to a graph with some edges.
	Vertex <i>v</i> is connected to each other vertex  
	with probability <i>p</i> */
    public void addVertex(Vertex v, String name , float p){
	    vertices.add(v);
	    v.setName(name);
	    Iterator e=vertices.iterator();
	    for(int j=0; j<size(); j++){
		if (r.nextDouble()<=p) addEdge(v,(Vertex)e.next());
		else e.next();
	    }
	}
    
    /** 
	@return number of vertices in a graph
    */
    public int size(){return vertices.size();}
    
    /**
       Tries to add an edge between two  specified vertices.
       @return true when succeed, false other way.    
     */
    public boolean addEdge(Vertex u, Vertex v){
	if (u.equals(v)) return false;
	if (u.addEdge(v) && v.addEdge(u)) return true;
	else return false;}

    /**
       Tries to add random edge to a graph.
       @return true when succeed, false other way    
     */
    public boolean addRandomEdge(){
	Iterator e1=vertices.iterator();
	Iterator e2=vertices.iterator();
        int m=r.nextInt(size());
	for(int i=0; i<m; i++) e1.next();
	m=r.nextInt(size());
	for(int i=0; i<m; i++) e2.next();
	Vertex u=(Vertex)e1.next();
	Vertex v=(Vertex)e2.next();
	if (u==v) return false;
	return addEdge(u,v);
    }
    
    /** Print graph info.
	@see Vertex#print
     */
    public void print(){
	Iterator e=vertices.iterator();
	while(e.hasNext()){
	    ((Vertex)e.next()).print();
	    System.out.println();}}

    /** Count maximum degree in a graph.
	@return maximum degree in a graph.
     */
    public int delta(){
	Iterator e=vertices.iterator();
	int max=0, help=0;;
	while(e.hasNext()){
	    help=((Vertex)e.next()).deg();
	    if (help>max) max = help;
	}
	return max;
    }

    /** Count number of edges in a graph.
	@return number of edges in a graph.
     */
    public int countEdges(){
	Iterator e=vertices.iterator();
	int sum=0, help=0;;
	while(e.hasNext()){
	    help=((Vertex)e.next()).deg();
	    sum+=help;
	}
	return sum/2;
    }

    /** Each vertex in a graph is painted as a circle with radius in pixels.
	This method sets radius for all vertices equal to <i>r</i>.
	parameter r is a radius of Vertices in pixels.
	@see Vertex#paintState
	@see Vertex#setRadius
	@see Vertex#getRadius
    */
    public void setRadius(int r){
	Iterator e=vertices.iterator();
	while(e.hasNext())
	    ((Vertex)e.next()).setRadius(r);}
    

    /** First available vertex in a graph
     *  @return 
     *  first available vertex according to iterator over vertices, or <i>null</i> when all agree.
     *  @see Graph#iterator 
     *  @see Vertex#agree 
     */
    public Vertex firstAvailable(){
	Iterator e=vertices.iterator();
	Vertex help;
	while(e.hasNext()){
	    help=(Vertex)e.next();
	    if (! help.agree()) return help;
	}
	return null;
    } 

    /** Chooses one of the available vertices in a graph
     *  @return 
     *  available vertex or <i>null</i> when all agree
     *  @see Vertex#agree 
     */
    public Vertex randomAvailable(){
	Iterator e=vertices.iterator();
	Vertex help;
	ArrayList available = new ArrayList();
	while(e.hasNext()){
	    help=(Vertex)e.next();
	    if (! help.agree()) available.add(help);
	}
	if (available.size()>0) {
	Collections.shuffle(available);
	e=available.iterator();
	return (Vertex)e.next();
	} else 
	return null;
    } 
    
    /** Set states of vertices in a random manner.
     *  @see Vertex#randomState 
     */
    public void randomStates(){
	moves=0;
	Iterator e=vertices.iterator();
	while(e.hasNext())
	    ((Vertex)e.next()).randomState(r);
    }
    
    /** Print vertices in a graph
     *  @see Vertex#printState 
     */
    public void printStates(){
	Iterator e=vertices.iterator();
	while(e.hasNext())
	    ((Vertex)e.next()).printState();
    }
    
    /** Paint vertices in a graph using <i>graphics</i>
     *  @see Vertex#paintState 
     */
    public void paintStates(Graphics graphics){
	Iterator e=vertices.iterator();
	while(e.hasNext())
	    ((Vertex)e.next()).paintState(graphics);
    }
    /**
       Iterator over vertices of a graph.
     */
    public Iterator iterator(){return vertices.iterator();}

    /** Each vertex in a graph  has its coordinates.
	To paint a vertex in a proper place 
	this coordinates must be adjusted to actual Panel size.
	This is done by setting xFactor and yFactor in each vertex. */
           public void setFactors(int maxX, int maxY)
    {
	float xFactor=(float)maxX/Graph.MAXX;
	float yFactor=(float)maxY/Graph.MAXY;	
	Iterator e=vertices.iterator();
	while(e.hasNext())
	    ((Vertex)e.next()).setFactors(xFactor, yFactor);
    }

    
    private void prepareFile(String path){
	    try{		
	    PrintWriter out
		= new PrintWriter(new BufferedWriter(new FileWriter("skis.txt")));
	    Iterator e=iterator();
	    while(e.hasNext()) {
		out.print(((Vertex)e.next()).getName());
		out.print(" node");
		out.println();
	    }
	    out.println();
	    e=iterator();
	    while(e.hasNext()) {
		Vertex v=((Vertex)e.next());
		Iterator e2=v.iterator();
		while(e2.hasNext()) {
		    out.print(v.getName());
		    out.print(" ");
		    out.print(((Vertex)e2.next()).getName());
		    out.println(" edge");
		}
	    }	      	       	    
	    out.close();}
	    catch(IOException e){System.out.println("Problem with " + path);}	
    }

    /**
       Gets Coordinates given by graphplace, 
       to use this method graphplace must be available,
       and writing files must be allowed.
     */
    public void getCoordinates(int maxX, int maxY) {
	String path="skis.txt";
	prepareFile(path);
	try{
	    Runtime runtime = Runtime.getRuntime();
	    Process proc = 
		runtime.exec("graphplace -b " + maxX + "," + maxY + " -f " +  path);
	    InputStream inputStream =
		proc.getInputStream();
	    InputStreamReader inputStreamReader =
		new InputStreamReader(inputStream);
	    BufferedReader bufferedReader =
		new BufferedReader(inputStreamReader);
	    String line =new String();
	    boolean skip=false;
	    
	    //reading coordinates given by graphplace
	    //after blank line we can skip the rest of data
	    Iterator e=iterator();		
	    while ((line = bufferedReader.readLine()) != null) 
		//System.out.println(line);
		if (!skip) 
		    if (line.length()==0) skip=true;
		    else {
			String[] tabs=new String[10];
			tabs=(line.split(" "));
			Vertex v= (Vertex)e.next();
			v.setXCoordinate(Float.valueOf(tabs[1]).intValue());
			v.setYCoordinate(Float.valueOf(tabs[2]).intValue());
		    }
	    try {
		if (proc.waitFor() != 0) 
			System.out.println("exit value = " + proc.exitValue());
	    } catch (InterruptedException f) {System.out.println(f);}
	} catch (IOException e) {System.out.println(e);}
    }

    /**
       Random coordinates of all vertices in a graph
     */
    public void randomCoordinates(int maxX, int maxY) {
	Iterator e=iterator();
	while (e.hasNext()){
	    Vertex v= (Vertex)e.next();
	    v.setXCoordinate(r.nextInt(maxX));
	    v.setYCoordinate(r.nextInt(maxY));
	}
    }
}
