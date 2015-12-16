package graphExample;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DemonDialog extends JDialog {
    /** a graph displayed in a demonPanel */ 
    Graph graph; 
    /** main panel with a graph*/
    DemonPanel demonPanel;
    /** Dialog  with vertex desription, appears only for a moment*/
    VertexInfoDialog vInfoDialog=null;
    /** Dialogs  with selected vertices  desription*/
    ArrayList allVInfoDialogs=new ArrayList();
    /** full file name with data for graphplace*/
    String path;
    /** Label with move statistic */
    JLabel movesInfo;
    /** Label with graph statistic */
    JLabel graphInfo;
    public void setGraph(Graph graph){
	this.graph=graph;
	repaint();}
    
    /** Construct a Dialog to paint graph <i>graph</i>, titled <i>title </i>*/
    public DemonDialog(Graph graph,String title) {
	super();
	this.graph=graph;
        path=new String("skis.txt");
	setTitle(title);
	Container cp = getContentPane();
	demonPanel=new DemonPanel();
	cp.add(demonPanel);
	JPanel buttonsPanel = new JPanel();
	buttonsPanel.setLayout(new GridLayout(1,2));
	JButton moveButton = new JButton("move");
	moveButton.addActionListener(new MoveAction());
	JButton randomStatesButton = new JButton("random states");
	randomStatesButton.addActionListener(new RandomStatesAction());
	buttonsPanel.add(moveButton);
	buttonsPanel.add(randomStatesButton);

	JPanel infoPanel=new JPanel();
	infoPanel.setLayout(new GridLayout(1,2));
	movesInfo = new JLabel("",JLabel.CENTER);
	graphInfo = new JLabel("",JLabel.CENTER);
	infoPanel.add(movesInfo); 
	infoPanel.add(graphInfo); 
	refreshInfo();

	JPanel other=new JPanel();
	other.setLayout(new GridLayout(2,1));
	other.add(buttonsPanel);
	other.add(infoPanel);
	cp.add(other, BorderLayout.SOUTH);
     
	setSize(600,450);
	addWindowListener(new WL());
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
          
    //windows, wchich were closed be the user will be removed from the list <i>llVInfoDialogs</i>
    void refreshAllVertexInfo(){
	Iterator e=allVInfoDialogs.iterator();
	while(e.hasNext()){
	    VertexInfoDialog vID=(VertexInfoDialog)e.next();
	    if (vID.isVisible()) vID.refresh();
	    else e.remove();
	}
    }
    
    void refreshInfo(){
	movesInfo.setText("Moves: " + graph.moves);
	graphInfo.setText(" " + graph.size() + " vertices " + "    Delta: " + graph.delta());
    }

    void makeMove(Vertex v){
	v.move(Vertex.textInfo2); 
	graph.moves++;
	refreshAllVertexInfo();
	refreshInfo();
	demonPanel.repaint();
    }

    class  WL extends WindowAdapter {
	public void windowClosing(WindowEvent e){
	    Iterator it=allVInfoDialogs.iterator();
	    while(it.hasNext()){
		VertexInfoDialog vID=(VertexInfoDialog)it.next();
		vID.setVisible(false);
		vID.dispose();
	    }
	    allVInfoDialogs.clear();
	    dispose();
	}
    }	

    class MoveAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if (graph!=null) {
		Vertex v=graph.randomAvailable();
		if (v!=null) makeMove(v);
	    }
	}
    }

    class RandomStatesAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if (graph!=null) {
		graph.randomStates();
		graph.moves=0;
		refreshInfo();
		refreshAllVertexInfo();
	    }	    
	    demonPanel.repaint();
	}
    }
    class DemonPanel extends JPanel {
	private Vertex hold; // vertex being hold  
	public int maxX;
	public int maxY;
	public DemonPanel(){addMouseListener(new ML());}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    maxX=getSize().width-1;
	    maxY=getSize().height-1;
	    graph.setFactors(maxX,maxY);
	    Iterator e=graph.iterator();
	    while(e.hasNext()) {
		Vertex v=((Vertex)e.next());
		Iterator e2=v.iterator();
		while(e2.hasNext()) {
		    Vertex u=(Vertex)e2.next();
		    g.drawLine(v.getXCoordinate(),
			       v.getYCoordinate(),
			       u.getXCoordinate(),
			       u.getYCoordinate());
		}
	    }	 
	    graph.paintStates(g);
	}

	class ML extends MouseAdapter {
	    public void mousePressed(MouseEvent e){
		Iterator iterator=graph.iterator();
		Vertex v;
		if (e.getButton()==e.BUTTON1)
		    while(iterator.hasNext()) {
			v=((Vertex)iterator.next());
			if (v.distance(e.getX(), e.getY())
			    <v.getRadius()) 
			    {hold=v; return;}}
		/*		if (e.getButton()==e.BUTTON3) {
				while(iterator.hasNext()) {
				v=((Vertex)iterator.next());
				if (v.distance(e.getX(), e.getY())<v.getRadius()) {
				vInfoDialog = new VertexInfoDialog(v); 
				vInfoDialog.setVisible(true);
			    } */
			    return;
	    }
	
    
	    
	    public void mouseReleased(MouseEvent e){		
		if ((hold!=null) &&(e.getButton()==e.BUTTON1)){
		    if((hold.distance(e.getX(), e.getY())>3) && 
		       (e.getX()>0) && (e.getX()<maxX) &&
		       (e.getY()>0) && (e.getY()<maxY)) {
			hold.setCoordinates(e.getX(),e.getY());
			repaint();}
		    hold=null;
		}		   
		//if (e.getButton()==e.BUTTON3) vInfoDialog.setVisible(false);
		return;
	    }
       
    	    /*Selected vertex 
	      -- makes a move (left double click)
	      -- start window with a vertex information text
	     	    */
	    public void mouseClicked(MouseEvent e) {
		if ((e.getButton()==e.BUTTON1) && (e.getClickCount()==2)) {
		    Iterator iterator=graph.iterator();
		    Vertex v;
		    while(iterator.hasNext()) {
			v=((Vertex)iterator.next());
			if (v.distance(e.getX(), e.getY())<v.getRadius()) 
			    if (!v.agree()) {makeMove(v); return;}
		    }}
		if ((e.getButton()==e.BUTTON3) && (e.getClickCount()==2)) {
		    Iterator iterator=graph.iterator();
		    Vertex v;
		    while(iterator.hasNext()) {
			v=((Vertex)iterator.next());
			if (v.distance(e.getX(), e.getY())<v.getRadius()) {
			    JDialog d = new VertexInfoDialog(v); 
			    allVInfoDialogs.add(d);
			    d.setVisible(true);
			    return;}
		    }
		}
	    }
	}
    }
}
