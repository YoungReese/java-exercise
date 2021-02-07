package com.ly.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * liyang 2020-12-09
 * 测试错误停止线程的方式：使用volatile，陷入阻塞时，volatile是无法停止线程的
 * 此例中，生产者生产速度很快，消费者消费速度慢，所以阻塞对列满了以后，生产者会阻塞，等待消费者进一步消费，
 * 消费者不再消费后，将生产者canceled置为true（producer.canceled = true;），但是生产者仍然阻塞
 *
 * 结果：
 * ...
 * 1100被消费了
 * 2100是100的倍数,被放到仓库中了。
 * 消费者不需要更多数据了。
 * true
 */
public class TestWrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        //一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}

class Producer implements Runnable {
    public volatile boolean canceled = false;
    BlockingQueue storage;
    public Producer() {}
    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                     storage.put(num); // put()操作阻塞后，没有线程来唤醒他！
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

class Consumer {
    BlockingQueue storage;
    public Consumer() {}
    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}