import org.junit.Test;

public class TestStringConstantPool {

    @Test
    public void test() {
        String str1 = "liyang";
        String str2 = new String("liyang");
        System.out.println(str1 == str2); // false

        String str3 = new String("liyang").intern(); // Native方法，会检查它所对应的内容是否在常量池中，是的话返回常量池中该常量的地址
        System.out.println(str1 == str3); // true

        String str4 = "li";
        String str5 = "yang";
        String str6 = str4 + str5; // 会在堆上创建新的对象
        System.out.println(str1 == str6); // false

        String str7 = "li" + "yang"; // 常量池中的对象
        System.out.println(str1 == str7); // true


        String str8 = "HelloWorld";
        String str9 = "Hello" + "World";  // 字符串常量拼接任然是字符串常量
        System.out.println(str8 == str9); // true

        // 小结：尽量避免多个字符串拼接，因为这样会重新创建对象。（常量池创建/堆上创建）
        // 如果需要，可以使用 StringBuilder 或者 StringBuffer。
    }

}
