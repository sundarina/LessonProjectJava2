import java.io.Serializable;

public class CcoloredLine extends CLine implements Figure, ColorAble, Serializable {

    private int[] color = new int[3];

    public CcoloredLine(int x1, int y1, int x2, int y2, int[] color) {
        super(x1, y1, x2, y2);
        for (int i = 0; i < color.length; i++) {
            this.color[i] = color[i];
        }
    }

    public CcoloredLine(CLine line, int[] color) {
        super(line);
        for (int i = 0; i < color.length; i++) {
            this.color[i] = color[i];
        }
    }

    public CcoloredLine(CPoint cp1, CPoint cp2, int[] color) {
        super(cp1, cp2);
        for (int i = 0; i < color.length; i++) {
            this.color[i] = color[i];
        }
    }

    public int getColorR() {
        return color[0];
    }

    public int getColorG() {
        return color[1];
    }

    public int getColorB() {
        return color[2];
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "COLOR Line start: " + super.getStart() + ", end: " + super.getEnd() + ", color(" + getColorR() + ", " + getColorG() + ", " + getColorB() + ")";
    }

    @Override
    public void display() {
        System.out.println(this);
    }
}
