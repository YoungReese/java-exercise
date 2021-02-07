package com.ly.anonymousclass;

/**
 * liyang 2021-01-03
 * 使用匿名类重写父类方法
 *
 * 结果：
 * Anonymous subclass override father class
 *
 * Process finished with exit code 0
 */
public class TestAnonymousClass {
    public static void main(String[] args) {

        Father father = new Father() {
            @Override
            protected Object initialValue() {
                return "Anonymous subclass override father class";
            }
        };
        System.out.println(father.initialValue());
    }
}
