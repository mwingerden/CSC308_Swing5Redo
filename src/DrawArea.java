import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * The DrawArea class holds the ability to draw all the shapes the user called for appropriately.
 * There are also methods and private variables that are connected with the ActionListener in
 * Colors.java that help the class know what shape and color to draw on the panel.
 *
 * @author Matthew Wingerden
 * @version 1.0
 * @since 2023-1-02
 *
 */

public class DrawArea extends JPanel implements MouseListener, MouseMotionListener {
    private int x1,x2,y1,y2 = 0;
    private final ArrayList<ColoredShape> coloredShapes = new ArrayList<>();
    private String color = "BLACK";
    private String shape = "rectangle";
    private boolean dragging = false;

    /**
     * Constructor for the class that adds itself to the MouseListener and MouseMotionListener class.
     *
     **/
    DrawArea() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * This method paints all the shapes appropriately on the screen.
     *
     * @param g   Holds the ability to draw the shapes.
     **/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double angleRadians;
        double length;

        for(ColoredShape current : coloredShapes) {
            switch (current.getColor()) {
                case "BLACK" -> g.setColor(Color.BLACK);
                case "RED" -> g.setColor(Color.RED);
                case "BLUE" -> g.setColor(Color.BLUE);
                case "GREEN" -> g.setColor(Color.GREEN);
                case "YELLOW" -> g.setColor(Color.YELLOW);
                case "ORANGE" -> g.setColor(Color.ORANGE);
                case "PINK" -> g.setColor(Color.PINK);
            }
            if(current.getType() == ColoredShape.ShapeType.RECTANGLE) {
                g.fillRect(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1());
            }
            else if(current.getType() == ColoredShape.ShapeType.CIRCLE) {
                g.fillOval(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1());
            }
            else if(current.getType() == ColoredShape.ShapeType.ARC) {
                angleRadians = Math.atan2(-(current.getY2()-current.getY1()), current.getX2()-current.getX1());
                length = Math.toDegrees(angleRadians);
                g.fillArc(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1(), 0, (int) length);
            }
        }

        if(dragging) {
            g.setColor(Color.WHITE);
            switch (shape) {
                case "rectangle" -> g.fillRect(x1, y1, x2 - x1, y2 - y1);
                case "circle" -> g.fillOval(x1, y1, x2 - x1, y2 - y1);
                case "arc" -> {
                    angleRadians = Math.atan2(-(y2-y1), x2-x1);
                    length = Math.toDegrees(angleRadians);
                    g.fillArc(x1, y1, x2 - x1, y2 - y1, 0, (int) length);
                }
            }
        }
    }

    /**
     * Gets the coordinates when mouse is pressed.
     *
     * @param e   Holds information about the mouse.
     **/
    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    /**
     * Gets the second coordinates and adds the desired shape to the list.
     *
     * @param e   Holds information about the mouse.
     **/
    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        dragging = false;
        switch (shape) {
            case "rectangle" -> coloredShapes.add(new ColoredShape(x1, y1, x2, y2, color, ColoredShape.ShapeType.RECTANGLE));
            case "circle" -> coloredShapes.add(new ColoredShape(x1, y1, x2, y2, color, ColoredShape.ShapeType.CIRCLE));
            case "arc" -> coloredShapes.add(new ColoredShape(x1, y1, x2, y2, color, ColoredShape.ShapeType.ARC));
        }
        repaint();
    }

    /**
     * Updates the second coordinates and marks dragging to true so that the panel can draw the shape as it is moving.
     *
     * @param e   Holds information about the mouse.
     **/
    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        dragging = true;
        repaint();
    }

    /**
     * When the user sets the shape in the Colors class this method is called to set the shape to later add
     * to the list of shapes.
     *
     * @param shape   The type of shape that is going to be set to draw the next shape.
     **/
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     * When the user sets the color in the Colors class this method is called to set the color to later add
     * to the list of shapes.
     *
     * @param color   The type of color to be set for the next shape
     **/
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets rid of the last shape added to the list.
     *
     **/
    public void undo() {
        if(!coloredShapes.isEmpty()) {
            coloredShapes.remove(coloredShapes.size() - 1);
        }
        repaint();
    }

    /**
     * Clears the entire shape list.
     *
     **/
    public void erase() {
        coloredShapes.clear();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
