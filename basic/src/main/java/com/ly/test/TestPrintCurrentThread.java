package com.ly.test;

/**
 * liyang 2021-01-03
 *
 * 结果：
 * Thread[main,5,main]
 * Thread[SubThread,5,main]
 *
 * Process finished with exit code 0
 */
public class TestPrintCurrentThread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        new Thread(() -> System.out.println(Thread.currentThread()), "SubThread").start();
    }
}
