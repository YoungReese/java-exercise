package com.ly.juc.threadgate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * liyang 2020-12-28
 * 使用 可重新关闭的阀门 + 线程池 + 原子变量 实现5个线程任务一组同时执行
 */
public class TestThreadGate {
    public static void main(String[] args) {
        ThreadGate threadGate = new ThreadGate();
        AtomicInteger asCount = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    if (asCount.incrementAndGet() % 5 == 0) {
                        threadGate.open();
                        threadGate.close();
                    } else {
                        threadGate.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " execute");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
    }
}
