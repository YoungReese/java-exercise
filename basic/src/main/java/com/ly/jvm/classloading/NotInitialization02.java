package com.ly.jvm.classloading;

/**
 * liyang 2020-12-08
 * 被动引用02：通过数组定义来引用类，不会触发此类的初始化
 *
 * 结果：
 * Process finished with exit code 0
 */
public class NotInitialization02 {
    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }
}
