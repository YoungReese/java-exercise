package com.ly.test;

/**
 * liyang 2020-11-28
 */
public class TestConstantString {
    public static void main(String[] args) {
        // Create three strings in three different ways.
        String s1 = "Hello";
        String s2 = new StringBuffer("He").append("llo").toString();
        String s3 = s2.intern();
        String s4 = s2;

        // Determine which strings are equivalent using the ==
        // operator
        System.out.println("s1 == s2? " + (s1 == s2)); // false
        System.out.println("s1 == s3? " + (s1 == s3)); // true
        System.out.println("s1 == s3? " + (s1 == s4)); // false

        System.out.println();

        String m = "hello,world";
        String n = "hello,world";
        String u = new String(m); // 堆上创建新的对象，仅仅值是"hello，world"而已
        String v = new String("hello,world"); // 堆上创建新的对象，仅仅值是"hello，world"而已

        System.out.println(m == n); //true
        System.out.println(m == u); //false
        System.out.println(m == v); //false
        System.out.println(u == v); //false

    }
}
