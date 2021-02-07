package com.ly.thread.commom;

import java.util.LinkedList;

/**
 * liyang 2020-12-09
 * 使用wait/notify实现生产者消费者模式
 *
 * 结果：
 * ...
 * 拿到了99，现在仓库还剩下0
 * 生产了100，仓库里有了1个产品。
 * 拿到了100，现在仓库还剩下0
 *
 * Process finished with exit code 0
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {
        StorageQueue storageQueue = new StorageQueue();
        new Thread(new Producer(storageQueue)).start();
        new Thread(new Consumer(storageQueue)).start();
    }

    // 生产者
    static class Producer implements Runnable {
        private StorageQueue storageQueue;
        public Producer() {}
        public Producer(StorageQueue storageQueue) {
            this.storageQueue = storageQueue;
        }
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                storageQueue.put(i);
            }
        }
    }

    // 消费者
    static class Consumer implements Runnable {
        private StorageQueue storageQueue;
        public Consumer() {}
        public Consumer(StorageQueue storageQueue) {
            this.storageQueue = storageQueue;
        }
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                storageQueue.take();
            }
        }
    }

    // 消息队列
    static class StorageQueue {
        private int capacity = 10;
        private final LinkedList<Object> q = new LinkedList<>();
        public StorageQueue() {}
        public StorageQueue(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void put(int i) {
            while(q.size() == capacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            q.add(i);
            System.out.println("生产了" + i + "，仓库里有了" + q.size() + "个产品。");
            notify();
        }

        public synchronized void take() {
            while(q.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了" + q.poll() + "，现在仓库还剩下" + q.size());
            notify();
        }
    }

}
