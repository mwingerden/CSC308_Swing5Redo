import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The Colors class holds implementation and set up of the Java Swing GUI that can create a limited
 * amount of colored objects specified by the user.
 *
 * @author Matthew Wingerden
 * @version 1.0
 * @since 2023-1-02
 *
 */

public class Colors extends JFrame implements ActionListener {
    DrawArea drawArea = new DrawArea();

    /**
     * The main inside the class to demonstrate how to call and run the program.
     *
     * @param argv   Holds the arguments from the console log.
     *
     **/
    public static void main(String[] argv) {
        Colors colors = new Colors();
        colors.createAndShowGUI();
    }

    /**
     * This method creates the specific JPanels that hold the buttons and includes the drawing area
     * for the shapes. The method ends with adding all the panels appropriately in a border layout.
     *
     **/
    public void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.CYAN);
        JButton b1 = new JButton("Undo");
        JButton b2 = new JButton("Erase");
        southPanel.add(b1);
        southPanel.add(b2);

        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.PINK);
        String [] colors = {"Black", "Red", "Blue", "Green", "Yellow", "Orange", "Pink"};
        JComboBox<String> list = new JComboBox<>(colors);
        JRadioButton rectangle = new JRadioButton("Rectangle");
        JRadioButton circle = new JRadioButton("Circle");
        JRadioButton arc = new JRadioButton("Arc");
        rectangle.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(rectangle);
        group.add(circle);
        group.add(arc);
        GridLayout grid = new GridLayout(7, 1);
        westPanel.setLayout(grid);
        westPanel.add(list);
        westPanel.add(rectangle);
        westPanel.add(circle);
        westPanel.add(arc);

        JPanel centerPanel = drawArea;
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.GRAY);

        list.addActionListener(this);
        rectangle.addActionListener(this);
        circle.addActionListener(this);
        arc.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);

        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        setTitle("Colors");
        setSize(500, 500);
        setVisible(true);
    }

    /**
     * This method holds all the actions for all possible buttons on the GUI.
     *
     * @param e   Holds the action that was performed on the GUI.
     **/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("comboBoxChanged")) {
            JComboBox tmp = (JComboBox) e.getSource();
            drawArea.setColor(Objects.requireNonNull(tmp.getSelectedItem()).toString().toUpperCase());
        }
        else if(e.getActionCommand().equalsIgnoreCase("rectangle")
                || e.getActionCommand().equalsIgnoreCase("circle")
                || e.getActionCommand().equalsIgnoreCase("arc")) {
            drawArea.setShape(e.getActionCommand().toLowerCase());
        }
        else if(e.getActionCommand().equalsIgnoreCase("undo")) {
            drawArea.undo();
        }
        else if(e.getActionCommand().equalsIgnoreCase("erase")) {
            drawArea.erase();
        }
    }
}