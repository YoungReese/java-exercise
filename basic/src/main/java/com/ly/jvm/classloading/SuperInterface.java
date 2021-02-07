package com.ly.jvm.classloading;

public interface SuperInterface {
    static final int SUPER_CONSTANT = 1000;

    static void superStaticMethod() {
        System.out.println("super static method");
    }

    default void superDefaultMethod() {
        System.out.println("super default method");
    }

}