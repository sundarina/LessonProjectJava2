import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

abstract class AbstractFigureFabric {

    public abstract Figure rand();

    public abstract Figure getFigure(int n);
}


class ColorFigureFabric extends AbstractFigureFabric {

    public Figure rand() {
        return getFigure((int) (Math.random() * 3));
    }

    /**
     * фабричный метод
     * можно сколько угодно добавлять фигур
     */

    public Figure getFigure(int n) { //метод, что будет возвращать какуюто фигуру
        switch (n) {
            case 0:
                return new CcoloredPoint(3, 4, 222222);
            case 1:
                return new CcoloredLine(4, 5, 6, 7, 555555);
            case 2:
                return new ColorTriangle(new CPoint(1, 1), new CPoint(3, 5), new CPoint(6, 1), 678543);
            default:
                return null;
        }
    }
}

class AllFigureFabric extends AbstractFigureFabric {


    public Figure rand() {
        return getFigure((int) (Math.random() * 6));
    }

    /**
     * фабричный метод
     * можно сколько угодно добавлять фигур
     */

    public Figure getFigure(int n) { //метод, что будет возвращать какуюто фигуру
        switch (n) {
            case 0:
                return new CPoint(0, (int) (Math.random() * 50));
            case 1:
                return new CLine(1, 2, 3, 4);
            case 2:
                return new CcoloredPoint(3, 4, 222222);
            case 3:
                return new CcoloredLine(4, 5, 6, 7, 555555);
            case 4:
                return new ColorTriangle(new CPoint(1, 1), new CPoint(3, 5), new CPoint(6, 1), 678843);
            case 5:
                return new TriangleClass(new CPoint(3, 5), new CPoint(5, 3), new CPoint(6, 6));
            default:
                return null;
        }
    }
}


public class MainFigures extends JFrame implements ActionListener, ItemListener {

    JPanel panelCheckBox;
    JPanel panelFigurePaint;
    JPanel panelButton;

    JButton rePaint, clear;
    JCheckBox pointChk, colorPointChk, lineChk, colorLineChk, triangleChk, colorTriangleChk, colorFigireChk;

    public MainFigures() {


        panelFigurePaint = new JPanel();
        panelButton = new JPanel();
        panelCheckBox = new JPanel();
        clear = new JButton("Clear");

        pointChk = new JCheckBox("Point");
        colorPointChk = new JCheckBox("Color Point");
        lineChk = new JCheckBox("Line");
        colorLineChk = new JCheckBox("Color Line");
        triangleChk = new JCheckBox("Triangle");
        colorTriangleChk = new JCheckBox("Color Triangle");
        colorFigireChk = new JCheckBox("Color Figure");

        panelCheckBox.setLayout(new GridLayout(1, 7));
        panelCheckBox.add(pointChk);
        pointChk.addItemListener(this);
        panelCheckBox.add(colorPointChk);
        colorPointChk.addItemListener(this);
        panelCheckBox.add(lineChk);
        lineChk.addItemListener(this);
        panelCheckBox.add(colorLineChk);
        colorLineChk.addItemListener(this);
        panelCheckBox.add(triangleChk);
        triangleChk.addItemListener(this);
        panelCheckBox.add(colorTriangleChk);
        colorTriangleChk.addItemListener(this);
        panelCheckBox.add(colorFigireChk);
        colorFigireChk.addItemListener(this);

        panelButton.add(clear, new FlowLayout());
        clear.addActionListener(this);

        add(panelFigurePaint, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
        add(panelCheckBox, BorderLayout.NORTH);

        setSize(1000, 400);
        setVisible(true);
    }

    public static void print(Figure fig) {
//        if (fig.getClass().getName().equals("CPoint")) // возвращает строку
//            if (fig instanceof CPoint) // ртти динамическое приведение
//                System.out.println("This Point X = " + ((CPoint) fig).getX());
//            else
        fig.display();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == clear) {
            pointChk.setSelected(false);
            colorPointChk.setSelected(false);
            lineChk.setSelected(false);
            colorLineChk.setSelected(false);
            triangleChk.setSelected(false);
            colorTriangleChk.setSelected(false);
            colorFigireChk.setSelected(false);
            panelCheckBox.repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        boolean visible = false;
        if (itemEvent.getStateChange() == ItemEvent.DESELECTED)
            visible = false;
        else visible= true;
        if(itemEvent.getItemSelectable() == pointChk) {
            //object.setVisible(visible);
        } else if(itemEvent.getItemSelectable() == ) {
            
        }
    }


    public static void main(String[] args) {

        CPoint point1 = new CPoint(0, 0);
        CPoint point2 = new CPoint(1, 1);
        CPoint point3 = new CPoint(6, 6);


        // cсылка храниться в стеке Stack, экземпляр храниться в HEAP (куче)
        //	System.out.println(cp1); // автовызов метода toString()


        CcoloredPoint colorPoint = new CcoloredPoint(point1, 123456);
        CcoloredPoint colorPoint1 = new CcoloredPoint(1, 1, 333333);
        CcoloredPoint colorPoint2 = new CcoloredPoint(point1, 222222);
        CcoloredPoint colorPoint3 = new CcoloredPoint(new CPoint(1, 2), 444444);

        CLine line1 = new CLine(1, 1, 2, 2); // композиция
        CLine line2 = new CLine(point1, point2);
        CLine line3 = new CLine(new CPoint(3, 3), new CPoint(4, 4));
        /*
         * формально - по ссылке, по выделению памяти и жизни - то удалиться
		 * вместе с линией
		 */

        CcoloredLine colorLine1 = new CcoloredLine(line1, 555555);
        CcoloredLine colorLine2 = new CcoloredLine(point1, point2, 398948);
        CcoloredLine colorLine3 = new CcoloredLine(new CcoloredPoint(point3, 77777), new CcoloredPoint(3, 3, 666666), 898891);

        TriangleClass triangle1 = new TriangleClass(point1, new CPoint(3, 4), new CPoint(point3));
        TriangleClass triangle2 = new TriangleClass(line1, line2, colorLine2);

        ColorTriangle colorTriangle1 = new ColorTriangle(line2, new CLine(line1.getStart(), line3.getEnd()), line1, colorLine1.getColor());
        ColorTriangle colorTriangle2 = new ColorTriangle(colorTriangle1.getSideAB(), colorTriangle1.getSideBC(), colorTriangle1.getSideCA(),
                colorPoint3.getColor());

        TriangleClass triangle3 = new TriangleClass(point1, point2, new CPoint(3, 3));
        triangle3.lengthAB();

        //////////////////////////////////////////////////////////////////////////////////////

        AbstractFigureFabric simpleFigureFabric = new AllFigureFabric();

        Figure[] masFig = new Figure[10]; /*
                                             * обьединение под общим
											 * интерфейсом, каждый из обьектов
											 * содержит метод, описаный в
											 * интерф. который обьеденяет всех.
											 * класс обязан выполнить контракт -
											 * реализов метод
											 */


        for (int i = 0; i < masFig.length; i++) {
            masFig[i] = simpleFigureFabric.rand();
            System.out.print(masFig[i]);
            System.out.print("\n");
        }

        System.out.print("\n");

//		masCPoint[0] = ccp1; // реализация полиморфизма. ссылка и родительского
//								// класса, засовывать можно и этот класс и
//								// потомкка


        int countPoint = 0;
        CPoint[] masPoint = new CPoint[10];
        for (Figure figure : masFig) {
            //  System.out.println(figure.toString() + " instatce " + figure.getClass().getName());
            if (figure instanceof CPoint) {
                masPoint[countPoint++] = (CPoint) figure;
                print(figure);
            }
        }
        System.out.print("\n");

        int countLine = 0;
        CLine[] masLines = new CLine[10];
        for (Figure figure : masFig) {
            if (figure instanceof CLine) {
                masLines[countLine] = (CLine) figure;
                print(figure);
            }
        }
        System.out.print("\n");

        int countTriangle = 0;
        TriangleClass[] masTri = new TriangleClass[10];
        for (Figure figure : masFig) {
            if (figure instanceof TriangleClass) {
                masTri[countTriangle] = (TriangleClass) figure;
                print(figure);
            }
        }
        System.out.print("\n");


        int countColorAble = 0;
        ColorAble[] masColor = new ColorAble[10];
        for (Figure figure : masFig) {
            // System.out.println(figure.toString() + " instatce " + figure.getClass().getName());
            //print(figure);
            if (figure instanceof ColorAble) {
                masColor[countColorAble++] = (ColorAble) figure;
                print(figure);
            }
        }
        System.out.print("\n");

        MainFigures mainFigures = new MainFigures();
    }


}
