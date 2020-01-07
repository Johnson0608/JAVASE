package thread;

/*
* 本类主要用于测试retry用法
*
* 如果只保留注释1，循环到 i==5的时候，程序跳到retry的那一行开始执行，此时 i 的值未变，然后又是i==5，程序进入死循环一直执行4到6行；执行结果为0 1 2 3 4
*
* 如果只保留注释2，循环到 i==5的时候，程序跳到retry的那一行开始执行，注意此时 i 的值还是5，接着 i++（i 不是从0开始了），所以输出 0 1 2 3 4 6 7 8 9
*
* 说明：其实retry就是一个标记，标记程序跳出循环的时候从哪里开始执行，功能类似于goto。retry一般都是跟随者for循环出现，第一个retry的下面一行就是for循环，而且第二个retry的前面一般是 continue或是 break。
*
* */



public class RetryTest {

    public static void main(String[] args) {
        testRetry();
    }

    public static void testRetry() {
        //retry://注释1
        for (int i = 0; i < 10; i++) {
            retry: //注释2
            while (i == 5) {
                continue retry;
            }
            System.out.print(i + " ");
        }
    }
}
