package com.ly.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * liyang 2020-12-13
 * 演示一定产生死锁
 *
 * 结果：
 * Thread-0 获取了锁lock1，等待尝试获取锁lock2
 * Thread-1 获取了锁lock2，等待尝试获取锁lock1
 * 主线程结束！
 */

public class MustDeadLock {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " 获取了锁lock1，等待尝试获取锁lock2");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " 获取了锁lock1和锁lock2");
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " 获取了锁lock2，等待尝试获取锁lock1");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " 获取了锁lock2和锁lock1");
                }
            }
        });

        thread0.start();
        thread1.start();

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("主线程结束！");
    }

}
