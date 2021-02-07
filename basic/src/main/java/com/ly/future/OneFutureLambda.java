package com.ly.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * liyang 2020-12-04
 * 演示一个Future的使用方法，使用lambda重写call方法
 */
public class OneFutureLambda {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        };
        Future<Integer> future = threadPool.submit(callable);
        System.out.println(future.get());

        Future<Integer> future2 = threadPool.submit(() -> 2020);
        System.out.println(future2.get());

        threadPool.shutdown();
    }
}
