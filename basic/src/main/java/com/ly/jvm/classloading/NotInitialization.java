package com.ly.jvm.classloading;

/**
 * liyang 2020-12-08
 * 主动引用01：当虚拟机启动的时候会主动加载主类（包含main()方法的那个类）
 * 主动引用02：通过子类引用父类的静态字段，会导致父类的初始化
 * 被动引用01：通过子类引用父类的静态字段，不会导致子类初始化
 *
 * 结果：
 * NotInitialization init!
 * SuperClass init!
 * 123
 *
 * Process finished with exit code 0
 */
public class NotInitialization {
    static {
        System.out.println("NotInitialization init!");
    }

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
