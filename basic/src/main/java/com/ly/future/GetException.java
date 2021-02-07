package com.ly.future;

import java.util.concurrent.*;

/**
 * liyang 2020-12-04
 * 测试call方法中抛出异常的时候会在get的时候抛出，而不是立即抛出
 * 在get捕获的时候，异常都会被归为ExecutionException
 *
 * 结果：
 * task执行完成? true
 * 1
 * 2
 * 3
 * java.util.concurrent.ExecutionException: java.lang.IllegalArgumentException: Callable抛出异常
 * 	at java.util.concurrent.FutureTask.report(FutureTask.java:122)
 * 	at java.util.concurrent.FutureTask.get(FutureTask.java:192)
 * 	at com.ly.future.GetException.main(GetException.java:24)
 * Caused by: java.lang.IllegalArgumentException: Callable抛出异常
 * 	at com.ly.future.GetException$CallableTask.call(GetException.java:38)
 * 	at com.ly.future.GetException$CallableTask.call(GetException.java:35)
 * 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * 	at java.lang.Thread.run(Thread.java:748)
 * ExecutionException异常
 *
 */

public class GetException {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        Future<Integer> future = threadPool.submit(new CallableTask());

        Thread.sleep(1000);
        System.out.println("task执行完成? " + future.isDone());
        try {
            for (int i = 1; i <= 3; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");
        }
        threadPool.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
