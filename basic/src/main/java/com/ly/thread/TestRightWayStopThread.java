package com.ly.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * liyang 2020-12-09
 * 测试使用唯一正确的方法停止线程：interrupt
 *
 * 结果：
 * ...
 * 400被消费了
 * 1400是100的倍数,被放到仓库中了。
 * 消费者不需要更多数据了。
 * 生产者结束运行
 * java.lang.InterruptedException
 * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.reportInterruptAfterWait(AbstractQueuedSynchronizer.java:2014)
 * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2048)
 * 	at java.util.concurrent.ArrayBlockingQueue.put(ArrayBlockingQueue.java:353)
 * 	at com.ly.thread.TestRightWayStopThread$Producer.run(TestRightWayStopThread.java:48)
 * 	at java.lang.Thread.run(Thread.java:748)
 *
 * Process finished with exit code 0
 *
 */
public class TestRightWayStopThread {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了!");

        producerThread.interrupt();
    }

    static class Producer implements Runnable {
        BlockingQueue<Integer> storage;
        public Producer() {}
        public Producer(BlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000) { // && !Thread.currentThread().isInterrupted() 可以不加，因为while在try-catch块中
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + "是100的倍数,被放到仓库中了。");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者结束运行");
            }
        }
    }

    static class Consumer {
        BlockingQueue<Integer> storage;
        public Consumer() {}
        public Consumer(BlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            return !(Math.random() > 0.95);
        }
    }
}
