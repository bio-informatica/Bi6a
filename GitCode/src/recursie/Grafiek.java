
package recursie;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grafiek extends JFrame
        implements ActionListener {

//    GUI code omitted here...
    private JButton button;
    private JPanel panel;

    public static void main(String[] args) {
        Grafiek frame = new Grafiek();
        frame.setSize(700, 550);
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBackground(Color.white);
        window.add(panel);

        button = new JButton("Press me");
        window.add(button);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        Graphics paper = panel.getGraphics();
        long startTime, endTime;

        //iteratief
        for (int k = 0; k < 20; k++) {
        for (int i = 0; i < 500; i++) {
            startTime = System.nanoTime();
            System.out.println("Resultaat: " + Recursie.fibonacciIterative(i));
            endTime = System.nanoTime();
            final long tijd2 = (endTime - startTime) / 100000;
            int msec2 = (int) tijd2;
            paper.setColor(Color.red);
            paper.fillRect(i + 1, 500 - msec2, 4, 4);
        }

        for (int i = 0; i < 35; i++) {
            startTime = System.nanoTime();
            System.out.println("Resultaat: " + Recursie.fibonacciRecursive(i));
            endTime = System.nanoTime();
            final long tijd1 = (endTime - startTime) / 100000;
            int msec1 = (int) tijd1;
            paper.setColor(Color.blue);
            paper.fillOval(i + 1, 500 - msec1, 4, 4);
        }
        }
    }
}
