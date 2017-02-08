import java.io.Serializable;

public class CLine implements Figure, Serializable {

    private CPoint start;
    private CPoint end;

    // полиморфизм, перегрузка конструктора средством изменения сигнатуры метода

    public CLine(int x1, int y1, int x2, int y2) { // композиция. данные по
        // значению , существуют
        // только внутри линии
        this.start = new CPoint(x1, y1); // агрегация : создание нового обьекта,
        // который получает на ввод ссылки
        this.end = new CPoint(x2, y2);

    }

    public CLine(CPoint start, CPoint end) { // передача данных по ссылке
        this.start = start;
        this.end = end;
    }

    public CLine(CLine line) { // конструктор копирования
        super();
        this.start = line.getStart();
        this.end = line.getEnd();
    }

    public CPoint getStart() {
        return start;
    }

    public void setStart(CPoint start) {
        this.start = start;
    }

    public CPoint getEnd() {
        return end;
    }

    public void setEnd(CPoint end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CLine [start = " + start + ", end = " + end + "]";
    }

    // @Override
    // public String toString() {
    // return "CLine start: " + start + ", end: " + end;
    // }

    public double length() {
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + (Math.pow(start.getY() - end.getY(), 2)));
    }

    @Override
    public void display() {
        System.out.println(this);
    }
}
