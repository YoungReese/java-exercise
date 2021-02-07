package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式3：懒汉式（线程不安全）（不可用）
 */
public class Singleton03 {
    private static Singleton03 instance;

    private Singleton03() {
    }

    public static Singleton03 getInstance() {
        if (instance == null) {
            instance = new Singleton03();
        }
        return instance;
    }
}
