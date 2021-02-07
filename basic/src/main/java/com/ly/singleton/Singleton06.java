package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式6：懒汉式（同步代码块 + 双重检测，线程不安全）（不可用）
 */
public class Singleton06 {
    private static Singleton06 instance;

    private Singleton06() {
    }

    public static Singleton06 getInstance() {
        if (instance == null) {
            synchronized (Singleton06.class) {
                if (instance == null) {
                    instance = new Singleton06();
                }
            }
        }
        return instance;
    }
}
