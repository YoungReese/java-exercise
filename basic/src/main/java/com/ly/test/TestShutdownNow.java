package com.ly.test;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * liyang 2020-12-01
 * 测试1：shutdownNow之后中断正在执行的和已经提交在阻塞队列中的任务
 *
 * 结果：
 * pool-1-thread-2
 * pool-1-thread-7
 * pool-1-thread-9
 * pool-1-thread-10
 * pool-1-thread-6
 * pool-1-thread-5
 * pool-1-thread-4
 * pool-1-thread-3
 * pool-1-thread-1
 * pool-1-thread-8
 * pool-1-thread-9
 * pool-1-thread-7
 * pool-1-thread-2
 * pool-1-thread-5
 * pool-1-thread-6
 * pool-1-thread-4
 * pool-1-thread-10
 * pool-1-thread-3
 * pool-1-thread-8
 * pool-1-thread-1
 * pool-1-thread-9被中断了
 * pool-1-thread-7被中断了
 * pool-1-thread-1被中断了
 * pool-1-thread-10被中断了
 * pool-1-thread-3被中断了
 * pool-1-thread-8被中断了
 * pool-1-thread-4被中断了
 * pool-1-thread-2被中断了
 * pool-1-thread-6被中断了
 * 线程池是否terminated了：false
 * pool-1-thread-5被中断了
 * 剩下多少任务没有执行：70
 * 执行了多少任务：20
 * 线程池是否terminated了：true
 *
 * Process finished with exit code 0
 *
 * --------------------------------------
 *
 * 测试2：shutdownNow之后中断不做处理
 *
 * 结果：
 * 剩下多少任务没有执行：35
 * 执行了多少任务：65
 *
 * Process finished with exit code 0
 *
 */

public class TestShutdownNow {

    private static final AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,
                10,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()); // AbortPolicy：默认拒绝策略，不填也是这个

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                    ai.getAndIncrement();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "被中断了");
                }
            });
        }

        Thread.sleep(1500);
        List<Runnable> tasks = threadPool.shutdownNow();
        System.out.println("线程池是否terminated了：" + threadPool.isTerminated()); // false
        System.out.println("剩下多少任务没有执行：" + tasks.size());
        System.out.println("执行了多少任务：" + ai.get());
        System.out.println("线程池是否terminated了：" + threadPool.isTerminated()); // true
    }
}



//public class TestShutdownNow {
//    private static final AtomicInteger ai = new AtomicInteger(0);
//    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,
//                10,
//                0,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()); // AbortPolicy：默认拒绝策略，不填也是这个
//
//        for (int i = 0; i < 100; i++) {
//            threadPool.execute(ai::getAndIncrement);
//        }
//
//        List<Runnable> tasks = threadPool.shutdownNow();
//        System.out.println("剩下多少任务没有执行：" + tasks.size());
//        System.out.println("执行了多少任务：" + ai.get());
//    }
//}
