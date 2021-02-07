package com.ly.test;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * liyang 2020-12-10
 * 主要用来看源码
 */
public class MyTest02 {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(3);
        abq.offer("a", 2, TimeUnit.SECONDS);

        new SynchronousQueue();
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        System.out.println(Runtime.getRuntime().availableProcessors());


        Supplier supplier = () -> {
            return "Hello World!";
        };

        System.out.println(supplier.get());


        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(1, 2);

        atomicInteger.getAndIncrement();

        new AtomicBoolean();

        new LinkedBlockingQueue<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());


        Executors.newFixedThreadPool(3);

        Executors.newSingleThreadExecutor();

        Executors.newCachedThreadPool();

        Executors.newScheduledThreadPool(1);

        Executors.newScheduledThreadPool(1).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hi");
            }
        }, 0, TimeUnit.SECONDS);


        Executors.newWorkStealingPool();

        new DelayQueue<>();

        new Object();

        final Lock lock = new ReentrantLock();

        new ReentrantLock();
        //        AbstractQueuedSynchronizer;

        lock.newCondition();

        Integer integer = new Integer(1222);

        new Thread();

        new Semaphore(2);

        new CountDownLatch(10);

        new CyclicBarrier(6);

        new Object();

        new LinkedBlockingQueue<>();

        new SynchronousQueue<>();

        new Thread().join();

        new CompletionService<Integer>() {
            @Override
            public Future<Integer> submit(Callable<Integer> task) {
                return null;
            }

            @Override
            public Future<Integer> submit(Runnable task, Integer result) {
                return null;
            }

            @Override
            public Future<Integer> take() throws InterruptedException {
                return null;
            }

            @Override
            public Future<Integer> poll() {
                return null;
            }

            @Override
            public Future<Integer> poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }
        };


        new ReentrantReadWriteLock();

        new LinkedTransferQueue<Object>();

        new ThreadLocal<>();

        new ConcurrentHashMap<>();

        new ConcurrentLinkedQueue<>();

        new DelayQueue<>();

        new LinkedBlockingDeque<>();

        new ForkJoinPool();

        new ReentrantLock();

        LockSupport.park();

    }
}
