package com.ly.thread.commom;

import java.util.concurrent.locks.ReentrantLock;

/**
 * liyang 2020-12-10
 * 演示sleep期间不释放lock（lock需要手动释放！）
 */
public class TestThreadSleepDontReleaseLock implements Runnable {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        TestThreadSleepDontReleaseLock r = new TestThreadSleepDontReleaseLock();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " got the lock");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " exit and release the lock");
    }

}
