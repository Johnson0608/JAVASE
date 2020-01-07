package enumtest;

public class EnumTest {
    public static void main(String[] args) {
        /**
         * 测试枚举的values()
         *
         */
        String s = Color.getValue(0).getDesc();
        System.out.println("获取的值为:" + s);


        /**
         * 测试枚举的valueof,里面的值可以是自己定义的枚举常量的名称
         * 其中valueOf方法会把一个String类型的名称转变成枚举项，也就是在枚举项中查找字面值和该参数相等的枚举项。
         */

        // value of  如果没有值会报错？
        Color color = Color.valueOf("3");
        System.out.println(color.getDesc());

        /**
         * 测试枚举的toString()方法
         */

        Color s2 = Color.getValue(0);
        System.out.println("获取的值为:" + s2.toString());

    }
}
