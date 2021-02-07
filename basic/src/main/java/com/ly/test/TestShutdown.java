package com.ly.test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * liyang 2020-12-01
 * 测试shutdown，和模拟shutdown之后提交任务，复现被决绝情况
 *
 * 结果：
 * ...
 * pool-1-thread-2: 3
 * pool-1-thread-8: 1
 * 线程池是否shutdown了：false
 * pool-1-thread-10: 0
 * pool-1-thread-5: 8
 * 线程池是否shutdown了：true
 * 线程池是否terminated了：false
 * Exception in thread "main" java.util.concurrent.RejectedExecutionException:
 * Task com.ly.test.ShutdownTask@4dc63996 rejected from java.util.concurrent.ThreadPoolExecutor@d716361
 * [Shutting down, pool size = 10, active threads = 10, queued tasks = 80, completed tasks = 10]
 * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
 * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
 * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
 * 	at com.ly.test.TestShutdown.main(TestShutdown.java:57)
 * pool-1-thread-1: 10
 * pool-1-thread-8: 16
 * ...
 * pool-1-thread-9: 99
 * pool-1-thread-3: 98
 *
 * Process finished with exit code 1
 */
public class TestShutdown {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,
                10,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()); // AbortPolicy：默认拒绝策略，不填也是这个

        for (int i = 0; i < 100; i++) {
            threadPool.execute(new ShutdownTask());
        }

        Thread.sleep(500);
        System.out.println("线程池是否shutdown了：" + threadPool.isShutdown()); // false
        // shutdown之后继续执行正在执行的和已经提交在阻塞队列中的任务
        threadPool.shutdown();
        System.out.println("线程池是否shutdown了：" + threadPool.isShutdown()); // true
        System.out.println("线程池是否terminated了：" + threadPool.isTerminated()); // false

        // 此时线程池已经是SHUTDOWN状态了，我继续提交一个任务，默认拒绝策略下抛出异常
        threadPool.execute(new ShutdownTask()); // RejectedExecutionException

    }
}

class ShutdownTask implements Runnable {
    private static final AtomicInteger ai = new AtomicInteger(0);
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + ": " + ai.getAndIncrement());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}