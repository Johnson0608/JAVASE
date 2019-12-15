package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcepTest {

    public static void main(String args[]) {

        // 1. 捕获异常
        try {
            int a[] = new int[2];
            /*System.out.println("Access element three :" + a[3]);*/
            System.out.println("Access element three :" + a[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception thrown  :" + e);
            System.out.println("Out of the block");
        }

        //  2.多重捕获
        try {
            String fileName = "/app";
            FileInputStream file = new FileInputStream(fileName);
            byte x = (byte) file.read();
        } catch (FileNotFoundException f) { // Not valid!
            f.printStackTrace();
            System.out.println("1号异常");
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("2号异常");
        }


    }
}
