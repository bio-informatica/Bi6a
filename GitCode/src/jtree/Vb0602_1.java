package jtree;

// JTree
import javax.swing.*;
import javax.swing.tree.*;

public class Vb0602 extends JFrame {

    public static void main(String args[]) {
        JFrame frame = new Vb0602();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Voorbeeld 0602  JTree");
        frame.setContentPane(new Boom());
        frame.setVisible(true);
    }
}

// De boom
class Boom extends JTree {

    private DefaultTreeModel treeModel;

    public Boom() {
        // Maak de knopen
        DefaultMutableTreeNode wortel, kind1, kind2,
                kleinkind1, kleinkind2, kleinkind3, kleinkind4;

        wortel = new DefaultMutableTreeNode("root");
        kind1 = new DefaultMutableTreeNode("Kind1");
        kind2 = new DefaultMutableTreeNode("Kind2");
        kleinkind1
                = new DefaultMutableTreeNode("Kleinkind1");
        kleinkind2
                = new DefaultMutableTreeNode("Kleinkind2");
        kleinkind3
                = new DefaultMutableTreeNode("Kleinkind3");

        kleinkind4
                = new DefaultMutableTreeNode("Kleinkind4");

        // Voeg de kinderen aan hun ouders toe
        wortel.add(kind1);
        wortel.add(kind2);

        kind1.add(kleinkind1);
        kind1.add(kleinkind2);
        kind1.add(kleinkind3);
        kleinkind2.add(kleinkind4);
        // Maak het model uit de wortel
        treeModel = new DefaultTreeModel(wortel);
        // Voeg het model toe aan deze boom
        setModel(treeModel);
    }
}
