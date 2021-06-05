package com.ly.jvm.initializing;

/**
 * liyang 2021-06-05
 * 初始化阶段中，静态语句块只能访问到定义在它之前的类变量，定义在它之后的类变量只能赋值，不能访问
 */
public class IllegalAccess {

    /**
     * right way
     */
    static int i = 1;
    static {
        i = 0;                // 给变量赋值可以正常编译通过
        System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }

    /**
     * wrong way  =>  Illegal forward reference
     */
//    static {
//        b = 0;                // 给变量赋值可以正常编译通过
//        System.out.print(b);  // 这句编译器会提示“非法向前引用”
//    }
//    static int b = 1;
}
