package com.ly.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * liyang 2020-12-04
 * 实现Callable接口，重写call方法，利用future接收submit执行后的返回值
 */
public class OneFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<Integer> future = threadPool.submit(new CallableTask());
        System.out.println(future.get());
        threadPool.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
