import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.io.*;

class MyException extends Exception {

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

public class FileProject {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        File file = null;
//        //new File("res" + File.separator + "inputFile.info");
//        JFileChooser jf = new JFileChooser();
//        jf.showOpenDialog(null);
//        file = jf.getSelectedFile();
//
//        System.out.println(file);
//        System.out.println("file =" + file.length());
//
//        DataInputStream dataIn = null;
//
//        try (FileInputStream fin = new FileInputStream(file);) {
//            dataIn = new DataInputStream(fin);
//            System.out.println("data =" + dataIn.available());
//
//            while (dataIn.available() > 0) {
//                String stPoint = dataIn.readLine();
//                String[] attribPoint = stPoint.split(" ");
//
//                int x = Integer.parseInt(attribPoint[0]);
//                if (x > 10000) throw new MyException();
//                int y = Integer.parseInt(attribPoint[1]);
//
//                CPoint point = new CPoint(x, y);
//                System.out.println(point);
//                String allstr = point.getX() + " " + point.getY();
//
//                jf.showDialog(null, "Save");
//                File fileSave = jf.getSelectedFile();
//
//                DataOutputStream dataSave = new DataOutputStream(new FileOutputStream(fileSave));
//                dataSave.writeBytes(allstr);
//                dataSave.close();
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found");
//            return;
//
//        } catch (MyException e) {
//
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        writefile3();

        try {
            openfile3();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void openfile3() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objRead = new ObjectInputStream(new FileInputStream("res" + File.separator + "outObj.point"));
        CPoint p2 = (CPoint) objRead.readObject();
        System.out.println(p2);
    }

    public static void writefile3() throws FileNotFoundException, IOException {

        ObjectOutputStream objSave = new ObjectOutputStream(new FileOutputStream("res" + File.separator + "outObj.point"));
        CPoint point = new CPoint(33, 44);
        objSave.writeObject(point);

    }


}
