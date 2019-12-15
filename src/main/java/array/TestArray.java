package array;

public class TestArray {
    public static void main(String[] args) {

        // 数组大小
        int size = 10;
        // 定义数组
        // double myList[] = new double[size];  --不建议采用的方式
        // double[] myList = new double[size];  --建议采用的方式
        double[] myList = {1.9, 2.9, 3.4, 3.5};

        //  jdk1.5之后新的循环访问方式 打印所有数组元素
        for (double element : myList) {
            System.out.println(element);
        }
    }

    // 数组作为函数参数
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    //数组作为函数返回值
    public static int[] reverse(int[] list) {
        int[] result = new int[list.length];

        for (int i = 0, j = result.length - 1; i < list.length; i++, j--) {
            result[j] = list[i];
        }
        return result;
    }

}
