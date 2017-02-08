import java.io.Serializable;

public class ColorTriangle extends TriangleClass implements Figure, ColorAble, Serializable {

    private int[] color = new int[3];

    public ColorTriangle(CLine ab, CLine bc, CLine ca, int[] color) {
        super(ab, bc, ca);
        for (int i = 0; i < color.length; i++) {
            this.color[i] = color[i];
        }
    }

    public ColorTriangle(CPoint apexA, CPoint apexB, CPoint apexC, int[] color) {
        super(apexA, apexB, apexC);
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
        for (int i = 0; i < color.length; i++) {
            this.color[i] = color[i];
        }
    }

    @Override
    public String toString() {
        return "COLOR Triangle A " + super.getSideAB() + ", B " + super.getSideBC() + ", C " + super.getSideCA() + ", color(" + getColorR() + ", " + getColorG() + ", " + getColorB() + ")";
    }

    @Override
    public void display() {
        System.out.println(this);
    }


}
