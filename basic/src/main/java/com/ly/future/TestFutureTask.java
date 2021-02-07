package com.ly.future;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * liyang 2020-12-04
 * public class FutureTask<V> implements RunnableFuture<V>
 * public interface RunnableFuture<V> extends Runnable, Future<V>
 *
 * 因此，FutureTask既可以当做Runnable使用，自身也会保留返回值，
 * 所以与线程池（线程）执行方式无关（execute or submit or start）
 *
 * 结果：
 * -734564877
 * 5050
 * ---------------
 * 500500
 * ---------------
 * 55
 *
 * Process finished with exit code 0
 */

public class TestFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        FutureTask<Integer> futureTask = new FutureTask<>(() -> new Random().nextInt());
        executorService.submit(futureTask);
        System.out.println(futureTask.get());

        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            int res = 0;
            for (int i = 1; i <= 100; i++) {
                res += i;
            }
            return res;
        });
        executorService.execute(futureTask2);
        System.out.println(futureTask2.get());
        executorService.shutdown();

        System.out.println("---------------");
        FutureTask<Integer> futureTask3 = new FutureTask<>(() -> {
            int res = 0;
            for (int i = 1; i <= 1000; i++) {
                res += i;
            }
            return res;
        });
        new Thread(futureTask3).start();
        System.out.println(futureTask3.get());

        System.out.println("---------------");
        new Thread(new FutureTask<Integer>(() -> {
            int res = 0;
            for (int i = 1; i <= 10; i++) {
                res += i;
            }
            System.out.println(res);
            return res;
        }) ).start();
    }
}
