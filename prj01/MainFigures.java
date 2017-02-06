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


    String path = "res" + File.separator + "source.txt";

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

    FileInputStream fileInputStreamX = null;
    FileOutputStream fileOutputStreamX = null;
    FileWriter writer = null;
    FileReader reader = null;


    String pathCPoint = "res" + File.separator + "CPoint.txt";
    String pathCcoloredPoint = "res" + File.separator + "CcoloredPoint.txt";
    String pathCLine = "res" + File.separator + "CLine.txt";
    String pathCcoloredLine = "res" + File.separator + "CcoloredLine.txt";
    String pathTriangle = "res" + File.separator + "TriangleClass.txt";
    String pathColorTriangle = "res" + File.separator + "ColorTriangle.txt";


    public MainFigures() throws IOException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        AbstractFigureFabric simpleFigureFabric = new AllFigureFabric();

        masFig = new Figure[10]; /*
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
            if (figure instanceof CPoint) {
                masPoint[countPoint++] = (CPoint) figure;
                //if (figure instanceof CPoint)
                figureWriter("CPoint", pathCPoint);
            }
        }


//        try {
//
//            fileOutputStreamX = new FileOutputStream("res" + File.separator + "sourceX.txt");
//
//
//            for (Figure figure : masFig) {
//                if (figure instanceof CPoint) {
//                    if (figure.getClass().getName().equals("CPoint")) {
//                        fileOutputStreamX.write(((CPoint) figure).getX());
//                       // fileOutputStreamX.write(((CPoint) figure).getY());
//                    }
//                }
//            }
//
//            fileOutputStreamX.flush();
//
//
//        } finally {
//
//            if (fileOutputStreamX != null) {
//                fileOutputStreamX.close();
//            }
//        }


        int countLine = 0;
        masLines = new CLine[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CLine) {
                masLines[countLine++] = (CLine) figure;
                // if (figure instanceof CLine)
                figureWriter("CLine", pathCLine);
            }
        }

        int countTriangle = 0;
        masTri = new TriangleClass[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof TriangleClass) {
                masTri[countTriangle++] = (TriangleClass) figure;
                // if (figure instanceof TriangleClass)
                figureWriter("TriangleClass", pathTriangle);
            }
        }

        int countColorAble = 0;
        masColor = new ColorAble[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof ColorAble) {
                masColor[countColorAble++] = (ColorAble) figure;
                if (figure instanceof CcoloredPoint)
                    figureWriter("CcoloredPoint", pathCcoloredPoint);
                if (figure instanceof CcoloredLine)
                    figureWriter("CcoloredLine", pathCcoloredLine);
                if (figure instanceof ColorTriangle)
                    figureWriter("ColorTriangle", pathColorTriangle);
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
                        if (masPoint[i].getClass().getName().equals("CPoint")) {
                            g.setColor(Color.BLACK);
                            if (masPoint[i] != null) {
                                g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 5, 5);
                            }
                        }
                    }
                }

                if (lineChk.isSelected()) {
                    for (int i = 0; i < cl; i++) {
                        if (masLines[i].getClass().getName().equals("CLine")) {
                            g.setColor(Color.BLACK);
                            if (masLines[i] != null)
                                g.drawLine(masLines[i].getStart().getX(), masLines[i].getStart().getY(), masLines[i].getEnd().getX(), masLines[i].getEnd().getY());
                        }
                    }
                }

                if (triangleChk.isSelected()) {
                    for (int i = 0; i < ct; i++) {
                        if (masTri[i].getClass().getName().equals("TriangleClass")) {
                            g.setColor(Color.BLACK);
                            if (masTri[i] != null)
                                g.drawPolygon(new int[]{masTri[i].getApexA().getX(), masTri[i].getApexB().getX(), masTri[i].getApexC().getX()}, new int[]{masTri[i].getApexA().getY(), masTri[i].getApexB().getY(), masTri[i].getApexC().getY()}, 3);
                        }
                    }
                }


                for (int i = 0; i < cc; i++) {
                    if (colorPointChk.isSelected()) {
                        try {
                            figureReader("CcoloredPoint", pathCcoloredPoint, cc);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (masColor[i].getClass().getName().equals("CcoloredPoint")) {
                            g.setColor(new Color(masColor[i].getColorR(), masColor[i].getColorG(), masColor[i].getColorB()));
                            if (masPoint[i] != null)
                                g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 5, 5);
                        }
                    }

                    if (colorLineChk.isSelected()) {
                        if (masColor[i].getClass().getName().equals("CcoloredLine")) {
                            g.setColor(new Color(masColor[i].getColorR(), masColor[i].getColorG(), masColor[i].getColorB()));
                            if (masLines[i] != null)
                                g.drawLine(masLines[i].getStart().getX(), masLines[i].getStart().getY(), masLines[i].getEnd().getX(), masLines[i].getEnd().getY());
                        }
                    }

                    if (colorTriangleChk.isSelected()) {

                        if (masColor[i].getClass().getName().equals("ColorTriangle")) {
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
        int x = 0;
        int y = 0;
        while ((line = reader.readLine()) != null) {
            String[] space = line.split(" ");

            for (int i = 0; i < space.length; i++) {
                String[] mas = space[i].split(",");

                for (int j = 0; j < mas.length; j++) {
                    for (Figure figure : masFig) {
                        if (figure.getClass().getName().equals(figureName)) {
                            if (figure instanceof CPoint) {
                                x = Integer.parseInt(mas[0]);
                                y = Integer.parseInt(mas[1]);
                                for (int k = 0; k < counter; k++) {
//                              if (masFig.getClass().getName().equals(figureName))
                                     masColor[k].setX(x);
                                     masColor[k].setY(y);
                                }

//
//                        if (figure.getClass().getName().equals(figureName)) {
//                        if(figureName.equals("CPoint"))

//                              if(masLines.getClass().getName().equals(figureName)) {
//                                   masLines[k].setStart(new CPoint(x, y));
//                                   masLines[k].setEnd(new CPoint(x,y));
//
//                                  }
                            }
                        }
                    }
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