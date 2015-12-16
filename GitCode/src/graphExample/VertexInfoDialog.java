package graphExample;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VertexInfoDialog extends JDialog {
    public TextArea opis;
    public Vertex v;
    public VertexInfoDialog(Vertex vertex){
	v=vertex;
	setTitle("Vertex: " + v.getName());
	Container cp = getContentPane();
	JPanel p=new JPanel();
	p.setLayout(new GridLayout(1,1));
	opis = new TextArea(v.getInfo());
	opis.setEditable(false);
	p.add(opis,new FlowLayout()); 
	cp.add(p);
	setSize(200,300);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void refresh(){
	opis.setText(v.getInfo());
    }
}
