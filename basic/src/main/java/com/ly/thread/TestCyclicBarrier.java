package com.ly.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * liyang 2020-12-04
 * 测试循环栅栏的用法
 *
 * 结果：
 * 运动员准备进场，全场欢呼............
 * pool-1-thread-1 运动员，进场
 * pool-1-thread-2 运动员，进场
 * pool-1-thread-3 运动员，进场
 * pool-1-thread-4 运动员，进场
 * pool-1-thread-5 运动员，进场
 * pool-1-thread-6 运动员，进场
 * 所有运动员入场，裁判员一声令下！！！！！
 * pool-1-thread-6  运动员出发
 * pool-1-thread-1  运动员出发
 * pool-1-thread-2  运动员出发
 * pool-1-thread-3  运动员出发
 * pool-1-thread-4  运动员出发
 * pool-1-thread-5  运动员出发
 *
 * Process finished with exit code 0
 *
 */

public class TestCyclicBarrier {
    // 指定必须有6个运动员到达才行
    private static final CyclicBarrier barrier = new CyclicBarrier(6, () -> {
        System.out.println("所有运动员入场，裁判员一声令下！！！！！");
    });

    public static void main(String[] args) {
        System.out.println("运动员准备进场，全场欢呼............");
        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 运动员，进场");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "  运动员出发");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    service.shutdown();
                }
            });
        }
    }

}
