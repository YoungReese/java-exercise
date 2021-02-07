package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式2：饿汉式（静态代码块）（可用）
 */
public class Singleton02 {
    private static final Singleton02 INSTANCE;

    static {
        INSTANCE = new Singleton02();
    }

    private Singleton02() {}

    public static Singleton02 getInstance() {
        return INSTANCE;
    }
}
