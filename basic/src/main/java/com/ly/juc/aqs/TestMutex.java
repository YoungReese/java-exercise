package com.ly.juc.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * liyang 2021-05-07
 * 测试 Mutex
 */
public class TestMutex {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 测试结果：
         *             mutex.lock():
         *         mutex.isLocked() => true
         * mutex.hasQueuedThreads() => false
         *          mutex.tryLock() => false
         *
         *           mutex.unlock():
         *         mutex.isLocked() => false
         * mutex.hasQueuedThreads() => false
         *          mutex.tryLock() => true
         *         mutex.isLocked() => true
         */
        baseTest();
        System.out.println();

        /**
         * 重复多次实验，因为一次可能加不加锁都成功，即 resource = 1
         * 不加锁方式
         *
         * resource = 2
         * count = 1014
         */
        int count = 0;
        while (resource <= 1 && count <= 10_000_000) {
            multipleRacingTest(false);
            ++count;
        }
        System.out.println("resource = " + resource);
        System.out.println("count = " + count);
        System.out.println();

        /**
         * 加锁方式
         *
         * resource = 1
         * count = 10000
         */
        count = 0;
        resetResource();
        while (resource <= 1 && count < 10_000) {
            multipleRacingTest(true);
            ++count;
        }
        System.out.println("resource = " + resource);
        System.out.println("count = " + count);

        threadPool.shutdown();
    }

    /**
     * 互斥锁的基础测试
     */
    public static void baseTest() {
        Mutex mutex = new Mutex();
        mutex.lock();
        System.out.println("            mutex.lock(): ");
        System.out.println("        mutex.isLocked() => " + mutex.isLocked());
        System.out.println("mutex.hasQueuedThreads() => " + mutex.hasQueuedThreads());
        System.out.println("         mutex.tryLock() => " + mutex.tryLock());
        System.out.println(" ");

        mutex.unlock();
        System.out.println("          mutex.unlock(): ");
        System.out.println("        mutex.isLocked() => " + mutex.isLocked());
        System.out.println("mutex.hasQueuedThreads() => " + mutex.hasQueuedThreads());
        System.out.println("         mutex.tryLock() => " + mutex.tryLock());
        System.out.println("        mutex.isLocked() => " + mutex.isLocked());
        mutex.unlock();
    }

    private static ExecutorService threadPool = Executors.newFixedThreadPool(1000);
    private static Mutex lock = new Mutex();
    private static volatile int resource = 0;

    private static void resetResource() {
        resource = 0;
    }

    /**
     * 1000个线程同时更改同一个变量，这里这允许更改一次
     * 使用 CountDownLatch 让多个线程同时操作来模拟竞态
     */
    public static void multipleRacingTest(boolean useMutex) throws InterruptedException {
        resetResource();

        CountDownLatch startCountDownLatch = new CountDownLatch(1);
        CountDownLatch endCountDownLatch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; ++i) {
            threadPool.execute(() -> {
                try {
                    startCountDownLatch.await();  // 所有子线程都将在此等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changeResource(useMutex);
                endCountDownLatch.countDown();
            });
        }

        startCountDownLatch.countDown(); // 放行多个线程，营造竞态环境
        endCountDownLatch.await();       // 主线程等待多个线程运行完毕
    }

    /**
     * resource 等于0，这个线程就将 resource 自增1
     * @param useMutex
     */
    public static void changeResource(boolean useMutex) {
        try {
            if (useMutex) lock.lock();

            if (resource == 0) {
                ++resource;
            }
        } finally {
            if (useMutex) lock.unlock();
        }
    }
}
