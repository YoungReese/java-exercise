package com.ly.thread;

/**
 * liyang 2020-12-08
 * 问：既传入实现Runnable的接口，也重现Thread的run方法，会调用哪个？
 * 答：调用Thread的run方法！
 *
 * 原因：Thread的匿名内部类重写了run方法，导致Runnable传入的target根本不会被调用
 *
 * // Thread这个run方法被Thread的匿名内部类的run方法替代了
 * public void run() {
 *     if (target != null) {
 *         target.run();
 *     }
 * }
 *
 * public void run() {
 *     System.out.println("come from thread");
 * }
 *
 * 结果：
 * come from thread
 * come from runnable
 *
 * Process finished with exit code 0
 */

public class TestBothRunnableAndThread {

    public static void main(String[] args) {
        // come from thread
        new Thread( () -> System.out.println("come from runnable") ) {
            @Override
            public void run() {
                System.out.println("come from thread");
            }
        }.start();

        // come from runnable
        new Thread( () -> System.out.println("come from runnable") ) {
            @Override
            public void run() {
                super.run();
            }
        }.start();

        // 什么也不做
        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();

        // 什么也不做
        new Thread().start();
    }

}
