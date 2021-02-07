package com.ly.dcl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * liyang 2020-12-23
 * 测试1000个线程同一时间启动争抢锁进行耗时操作的性能
 *
 * 结果：（以下为多次测试的结果）
 * 运行时间(ms)：1073
 * 运行时间(ms)：1076
 * 运行时间(ms)：1127
 */
public class TestDCL {

    private volatile static int calculatedValue = -1;
    private final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        CountDownLatch startCountDownLatch = new CountDownLatch(1);
        CountDownLatch endCountDownLatch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
            threadPool.execute(() -> {
                try {
                    startCountDownLatch.await();
                    calculation();
                    endCountDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        long startTime = System.currentTimeMillis();
        startCountDownLatch.countDown();
        endCountDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间(ms)：" + (endTime - startTime));

        threadPool.shutdown();
    }

    public static void calculation() {
        if (calculatedValue == -1) {
            synchronized (lock) {
                if (calculatedValue == -1) {
                    calculatedValue = expensiveOperation();
                }
            }
        }
    }

    public static int expensiveOperation() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2021;
    }
}
