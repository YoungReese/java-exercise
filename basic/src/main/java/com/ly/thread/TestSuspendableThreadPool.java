package com.ly.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * liyang 2020-11-30
 * 利用beforeExecute实现可暂停/继续的线程池
 *
 * 结果：
 * ...
 * hello world
 * hello world
 * hello world
 * 线程池被暂停了
 * 线程池继续执行
 * hello world
 * hello world
 * hello world
 * ...
 *
 * Process finished with exit code 0
 */
public class TestSuspendableThreadPool extends ThreadPoolExecutor {
    // 构造器
    public TestSuspendableThreadPool(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TestSuspendableThreadPool(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime, TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TestSuspendableThreadPool(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public TestSuspendableThreadPool(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory,
                                     RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    private boolean isPaused;
    private final ReentrantLock pauseLock = new ReentrantLock();
    private final Condition unpaused = pauseLock.newCondition();

    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            while (isPaused) unpaused.await(); // 等待知道信号来，或者中断来
        } catch (InterruptedException ie) {
            t.interrupt();
        } finally {
            pauseLock.unlock();
        }
    }

    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TestSuspendableThreadPool suspendableThreadPool = new TestSuspendableThreadPool(1,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };

        for (int i = 0; i < 10000; i++) {
            suspendableThreadPool.execute(task);
        }

        suspendableThreadPool.pause();
        System.out.println("线程池被暂停了");
        Thread.sleep(1000);
        suspendableThreadPool.resume();
        System.out.println("线程池继续执行");

        suspendableThreadPool.shutdown();


    }

}
