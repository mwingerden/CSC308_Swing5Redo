/**
 * The ColoredShape class holds the first clicked coordinates and the second clicked coordinates and the color.
 * The class is stored as a list in DrawArea and by calling the private variables are able to draw the colored
 * shape correctly.
 *
 * @author Matthew Wingerden
 * @version 1.0
 * @since 2023-1-02
 *
 */

public class ColoredShape {
    private final int x1, y1, x2, y2;
    private final String color;

    enum ShapeType {
        RECTANGLE,
        CIRCLE,
        ARC
    }
    ShapeType shapeType;

    /**
     * The constructor of the class that accepts the coordinates of the first and second click,
     * the color of the shape, and the type of shape it is.
     *
     * @param x1        The x coordinate of the click of the mouse.
     * @param y1        The y coordinate of the click of the mouse.
     * @param x2        The x coordinate of the release of the mouse.
     * @param y2        The y coordinate of the release of the mouse.
     * @param color     The color of the shape.
     * @param shapeType      The type of shape the class is.
     **/
    public ColoredShape(int x1, int y1, int x2, int y2, String color, ShapeType shapeType) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.shapeType = shapeType;
    }

    /**
     * Returns the type of shape the class is.
     *
     **/
    public ShapeType getType() {
        return shapeType;
    }

    /**
     * Returns the color of the current color.
     *
     **/
    public String getColor() {
        return color;
    }

    /**
     * Returns the x coordinate of the first click.
     *
     **/
    public int getX1() {
        return x1;
    }

    /**
     * Returns the y coordinate of the first click.
     *
     **/
    public int getY1() {
        return y1;
    }

    /**
     * Returns the x coordinate of the second click.
     *
     **/
    public int getX2() {
        return x2;
    }

    /**
     * Returns the y coordinate of the second click.
     *
     **/
    public int getY2() {
        return y2;
    }
}
