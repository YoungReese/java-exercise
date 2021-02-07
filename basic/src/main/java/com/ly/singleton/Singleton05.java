package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式5：懒汉式（同步代码块，线程不安全）（不可用）
 */
public class Singleton05 {
    private static Singleton05 instance;

    private Singleton05() {
    }

    public static Singleton05 getInstance() {
        if (instance == null) {
            synchronized (Singleton05.class) {
                instance = new Singleton05();
            }
        }
        return instance;
    }
}
