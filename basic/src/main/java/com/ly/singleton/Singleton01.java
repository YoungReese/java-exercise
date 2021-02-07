package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式1：饿汉式（静态常量）（可用）
 */
public class Singleton01 {
    private final static Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {};

    public static Singleton01 getInstance() {
        return INSTANCE;
    }
}
