import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.lang.NullPointerException;

abstract class AbstractFigureFabric {

    public abstract Figure rand();

    public abstract int[] randColor();

    public abstract Figure getFigure(int n);
}


class ColorFigureFabric extends AbstractFigureFabric {

    public Figure rand() {
        return getFigure((int) (Math.random() * 3));
    }

    public int[] randColor() {
        int[] r = new int[3];
        for (int i = 0; i < r.length; i++) {
            r[i] = (int) (Math.random() * 255);
        }
        return r;
    }

    /**
     * фабричный метод
     * можно сколько угодно добавлять фигур
     */

    public Figure getFigure(int n) { //метод, что будет возвращать какуюто фигуру
        switch (n) {
            case 0:
                return new CcoloredPoint((int) (Math.random() * 250), (int) (Math.random() * 800), this.randColor());
            case 1:
                return new CcoloredLine((int) (Math.random() * 250), 5, (int) (Math.random() * 250), (int) (Math.random() * 800), this.randColor());
            case 2:
                return new ColorTriangle(new CPoint((int) (Math.random() * 250), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 800)), this.randColor());
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

    public int[] randColor() {
        int[] r = new int[3];
        for (int i = 0; i < r.length; i++) {
            r[i] = (int) (Math.random() * 255);
        }
        return r;
    }

    public Figure getFigure(int n) { //метод, что будет возвращать какуюто фигуру
        switch (n) {
            case 0:
                return new CPoint((int) (Math.random() * 250), (int) (Math.random() * 800));
            case 1:
                return new CLine((int) (Math.random() * 250), (int) (Math.random() * 250), (int) (Math.random() * 800), (int) (Math.random() * 800));
            case 2:
                return new CcoloredPoint((int) (Math.random() * 250), (int) (Math.random() * 800), this.randColor());
            case 3:
                return new CcoloredLine((int) (Math.random() * 250), (int) (Math.random() * 250), (int) (Math.random() * 800), (int) (Math.random() * 800), this.randColor());
            case 4:
                return new ColorTriangle(new CPoint((int) (Math.random() * 250), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 800)), this.randColor());
            case 5:
                return new TriangleClass(new CPoint((int) (Math.random() * 50), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 50), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 50), (int) (Math.random() * 800)));
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

    Figure[] masFig;
    CPoint[] masPoint;
    CLine[] masLines;
    TriangleClass[] masTri;
    ColorAble[] masColor;


    public MainFigures() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        AbstractFigureFabric simpleFigureFabric = new AllFigureFabric();

        masFig = new Figure[20]; /*
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


//		masCPoint[0] = ccp1; // реализация полиморфизма. ссылка и родительского
//								// класса, засовывать можно и этот класс и
//								// потомкка


        int countPoint = 0;
        masPoint = new CPoint[masFig.length];
        for (Figure figure : masFig) {
            //  System.out.println(figure.toString() + " instatce " + figure.getClass().getName());
            if (figure instanceof CPoint) {
                masPoint[countPoint++] = (CPoint) figure;
                print(figure);
            }
        }

        int countLine = 0;
        masLines = new CLine[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CLine) {
                masLines[countLine++] = (CLine) figure;
                print(figure);
            }
        }

        int countTriangle = 0;
        masTri = new TriangleClass[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof TriangleClass) {
                masTri[countTriangle++] = (TriangleClass) figure;
                print(figure);
            }
        }

        int countColorAble = 0;
        masColor = new ColorAble[masFig.length];
        for (Figure figure : masFig) {
            // System.out.println(figure.toString() + " instatce " + figure.getClass().getName());
            //print(figure);
            if (figure instanceof ColorAble) {
                masColor[countColorAble++] = (ColorAble) figure;
                print(figure);
            }
        }

        final int cp = countPoint;
        final int cl = countLine;
        final int ct = countTriangle;
        final int cc = countColorAble;


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

        panelFigurePaint = new JPanel() {
            public void paint(Graphics g) {

                if (pointChk.isSelected()) {
                    for (int i = 0; i < cp; i++) {
                        if (masPoint[i].getClass().getName() == "CPoint") {
                            g.setColor(Color.BLACK);
                            if (masPoint[i] != null)
                                g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 5, 5);
                        }
                    }
                }

                if (lineChk.isSelected()) {
                    for (int i = 0; i < cl; i++) {
                        if (masLines[i].getClass().getName() == "CLine") {
                            g.setColor(Color.BLACK);
                            if (masLines[i] != null)
                                g.drawLine(masLines[i].getStart().getX(), masLines[i].getStart().getY(), masLines[i].getEnd().getX(), masLines[i].getEnd().getY());
                        }
                    }
                }

                if (triangleChk.isSelected()) {
                    for (int i = 0; i < ct; i++) {
                        if (masTri[i].getClass().getName() == "TriangleClass") {
                            g.setColor(Color.BLACK);
                            if (masTri[i] != null)
                                g.drawPolygon(new int[]{masTri[i].getApexA().getX(), masTri[i].getApexB().getX(), masTri[i].getApexC().getX()}, new int[]{masTri[i].getApexA().getY(), masTri[i].getApexB().getY(), masTri[i].getApexC().getY()}, 3);
                        }
                    }
                }

                for (int i = 0; i < cc; i++) {
                    if (colorPointChk.isSelected()) {
                        if (masColor[i].getClass().getName() == "CcoloredPoint") {
                            g.setColor(new Color(masColor[i].getColorR(), masColor[i].getColorG(), masColor[i].getColorB()));
                            if (masPoint[i] != null)
                                g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 5, 5);
                        }
                    }

                    if (colorLineChk.isSelected()) {
                        if (masColor[i].getClass().getName() == "CcoloredLine") {
                            g.setColor(new Color(masColor[i].getColorR(), masColor[i].getColorG(), masColor[i].getColorB()));
                            if (masLines[i] != null)
                                g.drawLine(masLines[i].getStart().getX(), masLines[i].getStart().getY(), masLines[i].getEnd().getX(), masLines[i].getEnd().getY());
                        }
                    }

                    if (colorTriangleChk.isSelected()) {

                        if (masColor[i].getClass().getName() == "ColorTriangle") {
                            g.setColor(new Color(masColor[i].getColorR(), masColor[i].getColorG(), masColor[i].getColorB()));
                            if (masTri[i] != null)
                                g.drawPolygon(new int[]{masTri[i].getApexA().getX(), masTri[i].getApexB().getX(), masTri[i].getApexC().getX()}, new int[]{masTri[i].getApexA().getY(), masTri[i].getApexB().getY(), masTri[i].getApexC().getY()}, 3);
                        }
                    }
                }
            }
        };


        panelButton.add(clear, new FlowLayout());
        clear.addActionListener(this);

        add(panelFigurePaint, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
        add(panelCheckBox, BorderLayout.NORTH);

        setSize(1000, 1000);
        panelFigurePaint.setVisible(false);
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
        boolean visible = true;

        if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
            visible = false;
        } else {
            visible = true;
        }


        if (itemEvent.getItemSelectable() == pointChk) {
            pointChk.setVisible(true);
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorPointChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == lineChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorLineChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == triangleChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorTriangleChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }
        if (itemEvent.getItemSelectable() == colorFigireChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }
    }


    public static void main(String[] args) {
        // MainFigures mainFigures = new MainFigures();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFigures();
            }
        });
    }
}

/**
 * ссылка храниться в стеке Stack, экземпляр храниться в HEAP (куче)
 * System.out.println(cp1); // автовызов метода toString()
 * <p>
 * CLine line1 = new CLine(1, 1, 2, 2); // композиция
 * <p>
 * Line l = new Line( new CPoint(3, 3), point2);
 * new CPoint(3,3)
 * формально - значение передаеться по ссылке, по выделению памяти и жизни - то удалиться вместе с линией
 * <p>
 * <p>
 * TriangleClass triangle3 = new TriangleClass(point1, point2, new CPoint(3, 3));
 * triangle3.lengthAB();
 */