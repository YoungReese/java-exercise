package com.ly.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * liyang 2020-11-30
 * 测试FixedThreadPoolOOM
 *
 * 为了快速复现OOM，Edit Configurations：
 * VM options: -Xmx8m -Xms8m
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 	at com.ly.test.TextFixedThreadPoolOOM.main(TextFixedThreadPoolOOM.java:20)
 */

public class TextFixedThreadPoolOOM {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            threadPool.execute(new MyTask());
        }

        threadPool.shutdown();

        try { // 等待直到所有任务完成
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyTask implements Runnable {

    @Override
    public void run() {
        try {
           Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}