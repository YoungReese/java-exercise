package com.ly.juc.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * liyang 2021-05-07
 * 测试 Mutex
 *
 * latch is singled ? false
 * 函数 f 开始调用
 * 函数 g 开始调用
 * 函数 g 调用完毕
 * latch is singled ? true
 * 函数 f 调用完毕
 */
public class TestBooleanLatch {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("latch is singled ? " + booleanLatch.isSignalled());
        threadPool.execute(() -> f());
        threadPool.execute(() -> g());

        booleanLatch.signal();
        System.out.println("latch is singled ? " + booleanLatch.isSignalled());
        threadPool.shutdown();
    }

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    public static BooleanLatch booleanLatch = new BooleanLatch();

    public static void f() {
        System.out.println("函数 f 开始调用");
        try {
            booleanLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("函数 f 调用完毕");
    }

    public static void g() {
        System.out.println("函数 g 开始调用");
        try {
            booleanLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("函数 g 调用完毕");
    }
}
