import java.io.Serializable;

public class CPoint implements Figure, Serializable {

    private int x;
    private int y;


    public CPoint(int x, int y) {
        super(); //конструктор класса Object
        this.x = x;
        this.y = y;
    }

    //конструктор копирования
    //дубликат точки

    //ссылка на существующую точку
    //извлекаем свои же параметры для внешнего использования

    public CPoint(CPoint point) {
        super();
        this.x = point.getX();
        this.y = point.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //переопределяем метод родительского класса
    //полиморфизм

    @Override
    public String toString() {

        return "CPoint: x = " + x + ", y = " + y;
    }

    @Override
    public void display() {
        System.out.println(this); //напечатает return "x = " + x + ", y = " + y;
    }
}
