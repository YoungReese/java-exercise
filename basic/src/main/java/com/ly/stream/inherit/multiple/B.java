package com.ly.stream.inherit.multiple;

/**
 * liyang 2021-04-06
 */
public interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}
