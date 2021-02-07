package com.ly.extend;

public class TestExtend {
    public static void main(String[] args) {
        Father01 f = new Son01();
        f.say();
        ((Son01) f).sonMethod();

        System.out.println();
        Father02 f2 = new Son02();
        f2.say();
    }
}
