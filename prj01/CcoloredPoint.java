import java.io.Serializable;

public class CcoloredPoint extends CPoint implements Figure, ColorAble, Serializable {

    private int[] color = new int[3];


    //private CPoint localpoint;

    public CcoloredPoint(int x, int y, int[] color) {
        super(x, y);
        for (int i = 0; i < color.length; i++) {
            this.color[i] = color[i];
        }
    }

    public CcoloredPoint(CPoint point, int[] color) {
        //super(point.getX(), point.getY()); //извлекаем с родителя
        super(point);
        //	localpoint = point; //треб констр родительского класса
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
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        for (int i = 0; i < color.length; i++) {
            c1 = color[0];
            c2 = color[1];
            c3 = color[2];
        }
            return "COLOR Point x = " + super.getX() + " y = " + this.getY() + ", color(" + c1 + ", " + c2 + ", " + c3 + ")" ;

    }

    @Override
    public void display() {
        System.out.println(this);
    }
}
