import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawArea extends JPanel implements MouseListener, MouseMotionListener {
    int x1,x2,y1,y2 = 0;
    ArrayList<ColoredShape> coloredShapes = new ArrayList<>();
    String color = "BLACK";
    String shape = "rectangle";
    boolean dragging = false;

    DrawArea() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

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
            if(current.getType() == ColoredShape.Type.RECTANGLE) {
                g.fillRect(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1());
            }
            else if(current.getType() == ColoredShape.Type.CIRCLE) {
                g.fillOval(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1());
            }
            else if(current.getType() == ColoredShape.Type.ARC) {
                angleRadians = Math.atan2(-current.getY1(), current.getX1());
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
                    angleRadians = Math.atan2(-y1, x1);
                    length = Math.toDegrees(angleRadians);
                    g.fillArc(x1, y1, x2 - x1, y2 - y1, 0, (int) length);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        dragging = false;
        switch (shape) {
            case "rectangle" -> coloredShapes.add(new ColoredShape(x1, y1, x2, y2, color, ColoredShape.Type.RECTANGLE));
            case "circle" -> coloredShapes.add(new ColoredShape(x1, y1, x2, y2, color, ColoredShape.Type.CIRCLE));
            case "arc" -> coloredShapes.add(new ColoredShape(x1, y1, x2, y2, color, ColoredShape.Type.ARC));
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        dragging = true;
        repaint();
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void undo() {
        if(!coloredShapes.isEmpty()) {
            coloredShapes.remove(coloredShapes.size() - 1);
        }
        repaint();
    }

    public void erase() {
        coloredShapes.clear();
        repaint();
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
