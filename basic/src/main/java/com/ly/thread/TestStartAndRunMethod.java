package com.ly.thread;

/**
 * liyang 2020-12-08
 *
 * 结果：
 * main
 * Thread-0
 *
 * Process finished with exit code 0
 */
public class TestStartAndRunMethod {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println(Thread.currentThread().getName());
        // 直接调用run方法，使用的是当前的线程
        task.run();                           // main
        // 调用start是使用new Thread的那个线程
        new Thread(task).start();             // Thread-0
    }
}
