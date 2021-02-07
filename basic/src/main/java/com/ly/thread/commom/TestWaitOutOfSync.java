package com.ly.thread.commom;

/**
 * 2020-12-09
 * wait/notify需要在同步代码块中执行
 */
public class TestWaitOutOfSync {
    final static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        lock.wait();   // java.lang.IllegalMonitorStateException
//        lock.notify(); // java.lang.IllegalMonitorStateException
    }
}
