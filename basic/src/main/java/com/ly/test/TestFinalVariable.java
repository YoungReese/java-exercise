package com.ly.test;

/**
 * liyang 2020-11-27
 *
 * 测试关键字final
 *
 * 类中final修饰的变量的赋值时机
 * 1、声明时赋值
 * 2、构造函数中赋值
 * 3、静态代码块中赋值（需要static修饰变量）
 *
 * 函数中final修饰的局部变量的赋值时机
 * 声明时赋值（因为不存在构造器和静态代码块，使用前赋值即可）
 */
public class TestFinalVariable {

    public TestFinalVariable() {
        this.salary2 = 1000;
    }

    // 测试final修饰变量的赋值时机
    // 声明时赋值
    final int salary = 999;

    // 构造函数中赋值
    final int salary2;
    public TestFinalVariable(int salary2) {
        this.salary2 = salary2;
    }

    // 静态代码块中赋值（需要static修饰变量）
    final static int salary3;
    static {
        salary3 = 1001;
    }

    public static void method() {
        final int weight;
        weight = 60; // local final变量，使用前赋值即可，但不可重新赋值
        System.out.println(weight);
    }

    public static void main(String[] args) {
        final Student student = new Student();
        student.bag = "c++ primer";
//        student.planet = "mars"; // 对象中的final变量无法重新赋值
//        student = new Student(); // final修饰的对象，不能重新指向其他对象
        method();
    }

}

