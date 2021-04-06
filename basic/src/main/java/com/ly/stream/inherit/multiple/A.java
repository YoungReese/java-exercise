package com.ly.stream.inherit.multiple;

/**
 * liyang 2021-04-06
 */
public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
