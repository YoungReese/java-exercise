package com.ly.test;

/**
 * liyang 2021-01-08
 * 写final域的重排序规则:
 * 1) JMM禁止编译器把final域的写重排序到构造函数之外
 * 2) 编译器会在final域的写之后，构造函数return之前，插入一个StoreStore屏障，
 * 这个屏障禁止处理器把final域的写冲排序到构造函数之外
 */
public class TestFinal {
    int a;
    final int b;
    static TestFinal o;
    final static int c = 18; // final和static修饰的变量必须在此初始化

    public TestFinal() {
        a = 2020;
        b = 2021; // final域b只能声明的时候赋值或者构造器中赋值
    }
    public static void main(String[] args) {
        o = new TestFinal();
        int x = o.a;
        int y = o.b;
        System.out.println(x);
        System.out.println(y);
        System.out.println(c);
    }
}
