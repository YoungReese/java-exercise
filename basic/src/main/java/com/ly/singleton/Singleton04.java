package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式4：懒汉式（同步方法，线程安全）（不推荐）
 */
public class Singleton04 {
    private static Singleton04 instance;

    private Singleton04() {
    }

    public synchronized static Singleton04 getInstance() {
        if (instance == null) {
            instance = new Singleton04();
        }
        return instance;
    }
}
