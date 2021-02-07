package com.ly.jvm.classloading;

/**
 * liyang 2020-12-08
 * 被动引用03：常量在编译阶段会存入调用类的常量池中，本质上没有直接引用
 *           到定义常量的类，因此不会触发常量的类的初始化（常量传播优化  ）
 *
 * 结果：
 * hello world
 *
 * Process finished with exit code 0
 */
public class NotInitialization03 {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
    }
}
