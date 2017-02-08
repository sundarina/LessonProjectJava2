import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

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

class SimpleFigureFabric extends AbstractFigureFabric {


    public Figure rand() {
        return getFigure((int) (Math.random() * 3));
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
                return new TriangleClass(new CPoint((int) (Math.random() * 50), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 50), (int) (Math.random() * 800)), new CPoint((int) (Math.random() * 50), (int) (Math.random() * 800)));
            default:
                return null;
        }
    }
}


public class MainFigures extends JFrame implements ActionListener, ItemListener {

    private JPanel panelCheckBox;
    private JPanel panelFigurePaint;
    private JPanel panelButton;

    private JButton clear;
    private JCheckBox pointChk, colorPointChk, lineChk, colorLineChk, triangleChk, colorTriangleChk;

    private Figure[] masFig;
    private CPoint[] masPoint;
    private CLine[] masLines;
    private TriangleClass[] masTri;
    private ColorAble[] masColor;
    private CcoloredPoint[] masColorPoint;
    private ColorTriangle[] masColorTriangle;
    private CcoloredLine[] masColorLine;

    FileWriter writer = null;

    private String pathCPoint = "res" + File.separator + "CPoint.txt";
    private String pathCcoloredPoint = "res" + File.separator + "CcoloredPoint.txt";
    private String pathCLine = "res" + File.separator + "CLine.txt";
    private String pathCcoloredLine = "res" + File.separator + "CcoloredLine.txt";
    private String pathTriangle = "res" + File.separator + "TriangleClass.txt";
    private String pathColorTriangle = "res" + File.separator + "ColorTriangle.txt";


    public MainFigures() throws IOException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AbstractFigureFabric simpleFigureFabric = new SimpleFigureFabric();
        AbstractFigureFabric colorFigureFabric = new ColorFigureFabric();

 /* обьединение под общим интерфейсом, каждый из обьектов содержит метод, описаный в интерф.
  класс обязан выполнить контракт - реализов метод*/

        masFig = new Figure[10];
        for (int i = 0; i < masFig.length; i++) {
            masFig[i] = simpleFigureFabric.rand();
            System.out.print(masFig[i]);
            System.out.print("\n");
        }

        masColor = new ColorAble[10];
        for (int i = 0; i < masColor.length; i++) {
            masColor[i] = (ColorAble) colorFigureFabric.rand();
            System.out.print(masColor[i]);
            System.out.print("\n");
        }

//		masCPoint[0] = ccp1; // реализация полиморфизма. ссылка и родительского
//								// класса, засовывать можно и этот класс и
//								// потомкка


        int countPoint = 0;
        masPoint = new CPoint[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CPoint) {
                masPoint[countPoint++] = (CPoint) figure;
                figureWriter("CPoint", pathCPoint);
            }
        }

        int countLine = 0;
        masLines = new CLine[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CLine) {
                masLines[countLine++] = (CLine) figure;
                figureWriter("CLine", pathCLine);
            }
        }

        int countTriangle = 0;
        masTri = new TriangleClass[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof TriangleClass) {
                masTri[countTriangle++] = (TriangleClass) figure;
                figureWriter("TriangleClass", pathTriangle);
            }
        }

        int countColorPoint = 0;
        masColorPoint = new CcoloredPoint[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof CcoloredPoint)
                masColorPoint[countColorPoint++] = (CcoloredPoint) colorAble;
            figureWriter("CcoloredPoint", pathCcoloredPoint);
        }

        int countColorLine = 0;
        masColorLine = new CcoloredLine[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof CcoloredLine)
                masColorLine[countColorLine++] = (CcoloredLine) colorAble;
            figureWriter("CcoloredLine", pathCcoloredLine);

        }

        int countColorTriangle = 0;
        masColorTriangle = new ColorTriangle[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof ColorTriangle)
                masColorTriangle[countColorTriangle++] = (ColorTriangle) colorAble;
            figureWriter("ColorTriangle", pathColorTriangle);
        }


        final int cp = countPoint;
        final int cl = countLine;
        final int ct = countTriangle;
        final int ccp = countColorPoint;
        final int ccl = countColorLine;
        final int cct = countColorTriangle;

        panelButton = new JPanel();
        panelCheckBox = new JPanel();
        clear = new JButton("Clear");

        pointChk = new JCheckBox("Point");
        colorPointChk = new JCheckBox("Color Point");
        lineChk = new JCheckBox("Line");
        colorLineChk = new JCheckBox("Color Line");
        triangleChk = new JCheckBox("Triangle");
        colorTriangleChk = new JCheckBox("Color Triangle");

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


        panelFigurePaint = new JPanel() {
            public void paint(Graphics g) {
//
//                try {
//                    fileInputStreamX = new FileInputStream("res" + File.separator + "CPoint.txt");
////                    byte [] x = new byte[4];
////                    byte [] y = new byte[4];
//                    int c;
////                    while (fileInputStreamX.read(x, 0, 4) != 0) {
////                        fileInputStreamX.read(y, 0, 4);
////                    }
//                    while ((c = fileInputStreamX.read()) != -1) {
//                        //g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 5, 5);
//
//                        for (int i = 0; i < cp; i++) {
//                            masPoint[i].setX(c);
//                        }
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (fileInputStreamX != null) {
//                        try {
//                            fileInputStreamX.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }


                if (pointChk.isSelected()) {
                    try {
                        figureReader("CPoint", pathCPoint, cp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < cp; i++) {
                        g.setColor(Color.BLACK);
                        if (masPoint[i] != null) {
                            g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 10, 10);
                        }
                    }
                }


                if (lineChk.isSelected()) {
                    try {
                        figureReader("CLine", pathCLine, cl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < cl; i++) {
                        g.setColor(Color.BLACK);
                        if (masLines[i] != null)
                            g.drawLine(masLines[i].getStart().getX(), masLines[i].getStart().getY(), masLines[i].getEnd().getX(), masLines[i].getEnd().getY());
                    }
                }

                if (triangleChk.isSelected()) {
                    try {
                        figureReader("TriangleClass", pathTriangle, ct);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < ct; i++) {
                        g.setColor(Color.BLACK);
                        if (masTri[i] != null)
                            g.drawPolygon(new int[]{masTri[i].getApexA().getX(), masTri[i].getApexB().getX(), masTri[i].getApexC().getX()}, new int[]{masTri[i].getApexA().getY(), masTri[i].getApexB().getY(), masTri[i].getApexC().getY()}, 3);
                    }
                }


                if (colorPointChk.isSelected()) {
                    try {
                        figureReader("CcoloredPoint", pathCcoloredPoint, ccp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < ccp; i++) {
                        g.setColor(new Color(masColorPoint[i].getColorR(), masColorPoint[i].getColorG(), masColorPoint[i].getColorB()));
                        if (masColorPoint[i] != null)
                            g.fillOval(masColorPoint[i].getX(), masColorPoint[i].getY(), 10, 10);
                    }
                }


                if (colorLineChk.isSelected()) {
                    try {
                        figureReader("CcoloredLine", pathCcoloredLine, ccl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < ccl; i++) {
                        g.setColor(new Color(masColorLine[i].getColorR(), masColorLine[i].getColorG(), masColorLine[i].getColorB()));
                        if (masLines[i] != null)
                            g.drawLine(masColorLine[i].getStart().getX(), masColorLine[i].getStart().getY(), masColorLine[i].getEnd().getX(), masColorLine[i].getEnd().getY());
                    }
                }

                if (colorTriangleChk.isSelected()) {

                    try {
                        figureReader("ColorTriangle", pathColorTriangle, cct);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < cct; i++) {
                        if (masColorTriangle[i].getClass().getName().equals("ColorTriangle")) {
                            g.setColor(new Color(masColorTriangle[i].getColorR(), masColorTriangle[i].getColorG(), masColorTriangle[i].getColorB()));
                            if (masTri[i] != null)
                                g.drawPolygon(new int[]{masColorTriangle[i].getApexA().getX(), masColorTriangle[i].getApexB().getX(), masColorTriangle[i].getApexC().getX()}, new int[]{masColorTriangle[i].getApexA().getY(), masColorTriangle[i].getApexB().getY(), masColorTriangle[i].getApexC().getY()}, 3);
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
    }


    public void figureWriter(String figureName, String path) throws IOException {

        writer = new FileWriter(path);
        try {
            for (Figure figure : masFig) {
                if (figure instanceof CPoint) {
                    if (figure.getClass().getName().equals(figureName)) {
                        writer.write(((CPoint) figure).getX() + "," + ((CPoint) figure).getY() + "\n");
                    }
                }

                if (figure instanceof CLine) {
                    if (figure.getClass().getName().equals(figureName)) {
                        writer.write(((CLine) figure).getStart().getX() + ", " + ((CLine) figure).getStart().getY() + " " + ((CLine) figure).getEnd().getX() + "," + ((CLine) figure).getEnd().getY() + "\n");
                    }
                }

                if (figure instanceof TriangleClass) {
                    if (figure.getClass().getName().equals(figureName)) {
                        writer.write(((TriangleClass) figure).getApexA().getX() + "," + ((TriangleClass) figure).getApexB().getX() + "," + ((TriangleClass) figure).getApexC().getX() + " " + ((TriangleClass) figure).getApexA().getY() + "," + ((TriangleClass) figure).getApexB().getY() + "," + ((TriangleClass) figure).getApexC().getY() + "\n");
                    }
                }
            }

            for (ColorAble colorAble : masColor) {
                if (colorAble instanceof CcoloredPoint) {
                    if (colorAble.getClass().getName().equals(figureName)) {
                        writer.write(((CcoloredPoint) colorAble).getX() + "," + ((CcoloredPoint) colorAble).getY() + "\n");
                    }
                }

                if (colorAble instanceof CcoloredLine) {
                    if (colorAble.getClass().getName().equals(figureName)) {
                        writer.write(((CcoloredLine) colorAble).getStart().getX() + ", " + ((CcoloredLine) colorAble).getStart().getY() + " " + ((CcoloredLine) colorAble).getEnd().getX() + "," + ((CcoloredLine) colorAble).getEnd().getY() + "\n");
                    }
                }

                if (colorAble instanceof TriangleClass) {
                    if (colorAble.getClass().getName().equals(figureName)) {
                        writer.write(((ColorTriangle) colorAble).getApexA().getX() + "," + ((ColorTriangle) colorAble).getApexB().getX() + "," + ((ColorTriangle) colorAble).getApexC().getX() + " " + ((ColorTriangle) colorAble).getApexA().getY() + "," + ((ColorTriangle) colorAble).getApexB().getY() + "," + ((ColorTriangle) colorAble).getApexC().getY() + "\n");
                    }
                }


            }
            writer.flush();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    public void figureReader(String figureName, String path, int counter) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] space = line.split(" ");
            String[] mas = space[0].split(",");
            if (space.length > 1) {
                String[] mas1 = space[1].split(",");

                for (Figure figure : masFig) {
                    if (figure instanceof CPoint) {
                        CPoint p = (CPoint) figure;
                        if (figure.getClass().getName().equals(figureName)) {
                            int x = 0;
                            int y = 0;
                            x = Integer.parseInt(mas[0]);
                            y = Integer.parseInt(mas[1]);
                            p.setX(x);
                            p.setY(y);
                        }
                    }

                    if (figure instanceof CLine) {
                        CLine l = (CLine) figure;
                        if (figure.getClass().getName().equals(figureName)) {

                            int x = 0;
                            int y = 0;
                            int x1 = 0;
                            int y1 = 0;

                            x = Integer.parseInt(mas[0]);
                            y = Integer.parseInt(mas[1]);

                            x1 = Integer.parseInt(mas1[0]);
                            y1 = Integer.parseInt(mas1[1]);

                            l.setStart(new CPoint(x, y));
                            l.setEnd(new CPoint(x1, y1));
                        }
                    }

                    if (figure instanceof TriangleClass) {
                        TriangleClass t = (TriangleClass) figure;
                        if (figure.getClass().getName().equals(figureName)) {

                            int x = 0;
                            int y = 0;
                            int x1 = 0;
                            int y1 = 0;
                            int x2 = 0;
                            int y2 = 0;

                            x = Integer.parseInt(mas[0]);
                            x1 = Integer.parseInt(mas[1]);
                            x2 = Integer.parseInt(mas[2]);

                            y = Integer.parseInt(mas1[0]);
                            y1 = Integer.parseInt(mas1[1]);
                            y2 = Integer.parseInt(mas1[2]);

                            t.setApexA(new CPoint(x, y));
                            t.setApexB(new CPoint(x1, y1));
                            t.setApexC(new CPoint(x2, y2));
                        }
                    }
                    break;
                }


                for (ColorAble colorAble : masColor) {
                    if (colorAble instanceof CcoloredPoint) {
                        CcoloredPoint cc = (CcoloredPoint) colorAble;
                        if (colorAble.getClass().getName().equals(figureName)) {
                            int x = 0;
                            int y = 0;
                            x = Integer.parseInt(mas[0]);
                            y = Integer.parseInt(mas[1]);
                            cc.setX(x);
                            cc.setY(y);
                        }
                    }

                    if (colorAble instanceof CcoloredLine) {
                        CcoloredLine cl = (CcoloredLine) colorAble;
                        if (colorAble.getClass().getName().equals(figureName)) {
                            int x = 0;
                            int y = 0;
                            int x1 = 0;
                            int y1 = 0;

                            x = Integer.parseInt(mas[0]);
                            y = Integer.parseInt(mas[1]);
                            x1 = Integer.parseInt(mas1[0]);
                            y1 = Integer.parseInt(mas1[1]);
                            cl.setStart(new CPoint(x, y));
                            cl.setEnd(new CPoint(x1, y1));
                        }
                    }

                    if (colorAble instanceof ColorTriangle) {
                        ColorTriangle ct = (ColorTriangle) colorAble;
                        if (colorAble.getClass().getName().equals(figureName)) { //выбор именно того обьекта, для которого сработал метод

                            int x = 0;
                            int y = 0;
                            int x1 = 0;
                            int y1 = 0;
                            int x2 = 0;
                            int y2 = 0;

                            x = Integer.parseInt(mas[0]);
                            x1 = Integer.parseInt(mas[1]);
                            x2 = Integer.parseInt(mas[2]);

                            y = Integer.parseInt(mas1[0]);
                            y1 = Integer.parseInt(mas1[1]);
                            y2 = Integer.parseInt(mas1[2]);

                            ct.setApexA(new CPoint(x, y));
                            ct.setApexB(new CPoint(x1, y1));
                            ct.setApexC(new CPoint(x2, y2));
                        }
                    }
                    break;
                }
            }
        }
        reader.close();
    }

    public static void main(String[] args) {
        // MainFigures mainFigures = new MainFigures();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFigures();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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