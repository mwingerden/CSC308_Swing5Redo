public class ColoredShape {
    private final int x1, y1, x2, y2;
    String color;

    enum Type {
        RECTANGLE,
        CIRCLE,
        ARC
    }

    Type type;

    public ColoredShape(int x1, int y1, int x2, int y2, String color, Type type) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
