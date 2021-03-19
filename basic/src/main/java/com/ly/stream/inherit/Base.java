package com.ly.stream.inherit;

@FunctionalInterface
public interface Base {

    void call();

    default void sayHi() {
        System.out.println("hi, base => by default method");
    }

    static void sayHello() {
        System.out.println("hello, base");
    }

}
