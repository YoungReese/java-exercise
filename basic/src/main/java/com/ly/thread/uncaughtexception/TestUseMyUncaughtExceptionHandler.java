package com.ly.thread.uncaughtexception;

/**
 * liyang 2020-12-10
 * 测试自己的MyUncaughtExceptionHandler
 */
public class TestUseMyUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new TestUseMyUncaughtExceptionHandler(), "MyThread-1").start();
        Thread.sleep(300);
        new Thread(new TestUseMyUncaughtExceptionHandler(), "MyThread-2").start();
        Thread.sleep(300);
        new Thread(new TestUseMyUncaughtExceptionHandler(), "MyThread-3").start();
        Thread.sleep(300);
        new Thread(new TestUseMyUncaughtExceptionHandler(), "MyThread-4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
