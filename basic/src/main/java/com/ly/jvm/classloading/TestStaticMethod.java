package com.ly.jvm.classloading;

/**
 * liyang 2020-12-08
 * 静态方法只有定义此方法的类和累的对象可以调用，建议： 类名.方法名()
 */
public class TestStaticMethod {
    public static void staticMethod() {
        System.out.println("static method");
    }

    public static void main(String[] args) {
        new TestStaticMethod().staticMethod();
        staticMethod();
    }
}
