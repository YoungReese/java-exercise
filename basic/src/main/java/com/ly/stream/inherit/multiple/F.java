package com.ly.stream.inherit.multiple;

/**
 * liyang 2021-04-06
 */
public class F implements A, E {
    public static void main(String[] args) {
        new F().hello();
    }

    /**
     * 通过 X.super.hello() 指定调用哪一个 hello()
     */
    @Override
    public void hello() {
        E.super.hello();
    }
}
