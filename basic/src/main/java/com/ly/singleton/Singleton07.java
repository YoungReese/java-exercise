package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式5：懒汉式（同步代码块 + 双重检测 + volatile，线程安全）（可用）
 */
public class Singleton07 {
    private volatile static Singleton07 instance; // 防止重排序

    private Singleton07() {
    }

    public static Singleton07 getInstance() {
        if (instance == null) {
            synchronized (Singleton07.class) {
                if (instance == null) {
                    instance = new Singleton07(); // 新建对象实际上有3个步骤
                }
            }
        }
        return instance;
    }
}
