package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 单例模式8：静态内部类（可用）
 * 静态内部类在外部类加载时不加载，在使用的时候才进行加载，类加载jvm保证线程安全
 */
public class Singleton08 {
    private Singleton08() {
    }

    public static Singleton08 getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Singleton08 INSTANCE = new Singleton08();
    }
}
