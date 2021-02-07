package com.ly.threadlocal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * liyang 2021-01-20
 *
 * ThreadLocal变量的基本原理:
 * 同一个 ThreadLocal<Integer> 所包含的对象（Integer类型变量），
 * 在不同的 Thread 中有不同的副本（实际是不同的实例，后文会详细阐述）
 */
public class ThreadLocalDemo {
    static int nThreads = 2;
    static CountDownLatch latch = new CountDownLatch(nThreads);

    static int count;
    static ThreadLocal<Integer> tlCount = new ThreadLocal<>();
    static ThreadLocal<Integer> tlCnt = ThreadLocal.withInitial(() -> 2020);

    private static class InnerRunnable implements Runnable {
        @Override
        public void run() {
            count++;

            if (tlCount.get() != null) {
                tlCount.set(tlCount.get().intValue() + 1);
            } else {
                tlCount.set(1999);
            }

            if (tlCnt.get() != null) {
                tlCnt.set(tlCnt.get().intValue() + 1);
            } else {
                tlCnt.set(1999);
            }

            System.out.println("count: " + count);
            System.out.println("tlCount: " + tlCount.get());
            System.out.println("tlCnt: " + tlCnt.get());

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new InnerRunnable();
        for (int i = 0; i < nThreads; i++) {
            new Thread(task).start();
        }
        latch.await();
        System.out.println("Finish run the task!");
    }
}
