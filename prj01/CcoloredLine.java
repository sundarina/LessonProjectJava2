
public class CcoloredLine extends CLine implements Figure, ColorAble {

    private int color;

    public CcoloredLine(int x1, int y1, int x2, int y2, int color) {
        super(x1, y1, x2, y2);
        this.color = color;
    }

    public CcoloredLine(CLine line, int color) {
        super(line);
        this.color = color;
    }

    public CcoloredLine(CPoint cp1, CPoint cp2, int color) {
        super(cp1, cp2);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "COLOR Line start: " + super.getStart() + ", end: " + super.getEnd() + ", color: " + getColor();
    }

    @Override
    public void display() {
        System.out.println(this);
    }
}
