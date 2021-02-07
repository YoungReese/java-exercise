package com.ly.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * liyang 2020-11-30
 *
 * 结果：
 * hi
 * hello world
 *
 * Process finished with exit code 0
 */

public class TestScheduledThreadPool {
    public static void main(String[] args) {

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);

        threadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, 3, TimeUnit.SECONDS); // 延时3秒执行任务

        threadPool.schedule(() -> {
            System.out.println("hi");
        }, 1, TimeUnit.SECONDS); // 延时1秒执行任务

        threadPool.shutdown();
    }
}
