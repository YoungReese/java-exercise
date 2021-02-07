package com.ly.test;

/**
 * liyang 2020-11-27
 * 字符串自面值常量和被final修饰的常量存储在方法区
 */
public class TestFinalString {

    public static String getHello() {
        return "hello";
    }

    public static void main(String[] args) {
        String a = "helloworld";
        final String b = "hello";
        String c = "hello";
        String d = b + "world";
        String e = c + "world";
        String f = "hello" + "world";
        System.out.println(a == d); // true
        System.out.println(a == e); // false
        System.out.println(a == f); // true

        // 由于使用函数函数获取，即使加了关键字final，编译器也没方法优化
        final String g = getHello();
        String h = g + "world";
        System.out.println(a == h); // false

    }

}
