package com.ly.test;

/**
 * liyang 2020-11-27
 *
 * 测试关键字final
 *
 * 构造器不能使用final修饰
 * 子类继承后，不可重写（override）赋类中被final修饰的方法
 *
 * 扩展：
 * 父类和子类中才可以存在同名的静态函数，但是意义是：各自绑定所在类，不存在重写（override）联系，也不是重写的关系！！！
 *
 * final修饰的类不可被继承：典型的String就是final类
 */

public class TestFinalMethod extends FatherClass {
    // 构造器不能使用final修饰
    public /* final */ TestFinalMethod() {
    }

    // method() cannot override
//    public final void method() {
//        System.out.println("子类重写父类final修饰的方法");
//    }

    public static void staticMethod() {
        System.out.println("这是子类中被static修饰的方法");
    }

    public static void main(String[] args) {
        TestFinalMethod obj = new TestFinalMethod(); // 这是父类中被final修饰的方法
        obj.method();   // 这是父类中被final修饰的方法
        staticMethod(); // 这是子类中被static修饰的方法
    }

}

class FatherClass {
    public final void method() {
        System.out.println("这是父类中被final修饰的方法");
    }

    public static void staticMethod() {
        System.out.println("这是父类中被static修饰的方法");
    }
}