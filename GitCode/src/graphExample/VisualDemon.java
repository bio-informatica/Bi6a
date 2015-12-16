package graphExample;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** The main window of an application is a VisualDemon instance*/
public class VisualDemon extends JApplet{
    public JTextField
        nField = new JTextField("6");
    public JTextField
        n2Field = new JTextField("3");
    public JTextField
        pField = new JTextField("0.4");
    /**actually selected graph type*/
    public int graphType=GraphTypes.arbitrary;
    /**actually selected algorithm*/
    public int algorithm=Algorithms.mds;

    public Graph g;

    public void init() {
       	JPanel graphTypePanel = new JPanel();
	graphTypePanel.setLayout(new GridLayout(2,2));
        JComboBox graphTypeList = new JComboBox(GraphTypes.graphTypes);
	graphTypeList.setSelectedIndex(graphType);
	graphTypeList.addActionListener(new GTL());
	graphTypePanel.add(new JLabel("Graph type:", JLabel.CENTER));
	graphTypePanel.add(graphTypeList);

        JComboBox algorithmsList = new JComboBox(Algorithms.algorithms);
	algorithmsList.setSelectedIndex(algorithm);
	algorithmsList.addActionListener(new ATL());
	graphTypePanel.add(new JLabel("Algorithm:", JLabel.CENTER));
	graphTypePanel.add(algorithmsList);

	JPanel parameters = new JPanel();
	parameters.setLayout(new GridLayout(3,2));
	parameters.add(new JLabel("Number of vertices", JLabel.CENTER));
	parameters.add(nField);
	parameters.add(new JLabel("Number of vertices in second set", JLabel.CENTER));
	n2Field.setEditable(false);
	parameters.add(n2Field);
	parameters.add(new JLabel("Density of a graph", JLabel.CENTER));
	parameters.add(pField);
	
	Container cp = getContentPane();
	cp.add(graphTypePanel, BorderLayout.NORTH);
	cp.add(parameters, BorderLayout.CENTER);
	JButton showButton = new JButton("show");
	showButton.addActionListener(new Show());
	cp.add(showButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args){
	JFrame frame = new JFrame("Self-Stabilization Demo");
	VisualDemon applet = new VisualDemon();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(applet);
	frame.setSize(400, 200);
	applet.init();
	applet.start();
	frame.setVisible(true);
    }

    //algorithms listener 
    class ATL implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    JComboBox cb=(JComboBox)e.getSource();
	    algorithm=cb.getSelectedIndex();
	}
    }

    //graph type listener 
    class GTL implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    JComboBox cb=(JComboBox)e.getSource();
	     if (cb.getSelectedItem()==GraphTypes.graphTypes[0]) graphType=GraphTypes.arbitrary;
	     if (cb.getSelectedItem()==GraphTypes.graphTypes[1]) graphType=GraphTypes.tree;
	     if (cb.getSelectedItem()==GraphTypes.graphTypes[2]) graphType=GraphTypes.bipartite;
	     
	     switch (graphType) {
	     case GraphTypes.arbitrary:  nField.setEditable(true);  
		 n2Field.setEditable(false); 
		 pField.setEditable(true);  break;
	     case GraphTypes.tree:  nField.setEditable(true);  
		 n2Field.setEditable(false); 
		 pField.setEditable(false);  break;
	     case  GraphTypes.bipartite:  nField.setEditable(true);  
		 n2Field.setEditable(true); 
		 pField.setEditable(true);  break;    
	     }
	}
    }
    //This action is provided when "show" button is pressed. 
    //Construct a graph and show in a DemonDialog window
    class Show implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    Vertex v=null;
	    String dialogTitle=null;
	    
	    switch (algorithm) {
	    case Algorithms.grundy:  
		v = new GrundyColoringVertex(); 
		dialogTitle=Algorithms.algorithms[Algorithms.grundy]; break; 
	    case Algorithms.trivial:  
		v = new TrivialColoringVertex(); 
		dialogTitle= Algorithms.algorithms[Algorithms.trivial]; break; 
	    case Algorithms.lf: 
		v= new LFColoringVertex(); 
		dialogTitle= Algorithms.algorithms[Algorithms.lf]; break;
	    case Algorithms.edge:
		v= new EdgeColoringVertex();
		dialogTitle= Algorithms.algorithms[Algorithms.edge]; break;
	    case Algorithms.mis: 
		v= new MISVertex(); 
		dialogTitle= Algorithms.algorithms[Algorithms.mis]; break;
	    case Algorithms.mds: 
		v= new MDSVertex(); 
		dialogTitle= Algorithms.algorithms[Algorithms.mds]; break;
	    }	
	    try{
		int n=0,n2=0; float f=0;
	    switch (graphType) {
	    case GraphTypes.arbitrary: //arbitrary graph
		n=Integer.parseInt(nField.getText());
		f=Float.parseFloat(pField.getText());
		g=new Graph(v, n, f); break;
	     case GraphTypes.tree: //a tree graph
		 n=Integer.parseInt(nField.getText());
		 g=new Graph(v, n); break;
	     case GraphTypes.bipartite: //bipartite graph
		 n=Integer.parseInt(nField.getText());
		 n2=Integer.parseInt(n2Field.getText());
		 f=Float.parseFloat(pField.getText());
		 g=new Graph(v, n, n2, f); break;
	    }
	    g.randomStates();
	    g.randomCoordinates(Graph.MAXX, Graph.MAXY);
	    /*try{
	    g.getCoordinates(Graph.MAXX, Graph.MAXY);
	    } catch (){
	    }*/
	    
	    JDialog d = new DemonDialog(g, dialogTitle); 
	    d.setVisible(true); 
	    } catch(NumberFormatException error)
		{JOptionPane.showMessageDialog(null, "Invalid numeric format", 
					       "alert",JOptionPane.ERROR_MESSAGE);} 
	}
    }
}
