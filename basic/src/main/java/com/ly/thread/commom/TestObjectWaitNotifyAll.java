package com.ly.thread.commom;

/**
 * liyang 2020-12-09
 * 测试notify, notifyAll
 * 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们
 *
 * 结果：
 * Thread-0 got the resource lock
 * Thread-0 wait to start
 * Thread-1 got the resource lock
 * Thread-1 wait to start
 * Thread-2 start and notified
 * Thread-2 go to the end
 * Thread-1 go to the end
 * Thread-0 go to the end
 *
 * Process finished with exit code 0
 */
public class TestObjectWaitNotifyAll implements Runnable {
    private static final Object resource = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new TestObjectWaitNotifyAll();
        Thread thread0 = new Thread(r);
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(() -> {
            synchronized (resource) {
                resource.notifyAll();
//                resource.notify();
                System.out.println(Thread.currentThread().getName() + " start and notified");
            }
            System.out.println(Thread.currentThread().getName() + " go to the end");
        });

        thread0.start();
        thread1.start();
        Thread.sleep(100); // 保证thread2在notify的时候，thread0、thread1已经在等待了
        thread2.start();

//        Thread.sleep(100);
//        // 使用notify，得到TERMINATED、WAITING
//        System.out.println(thread0.getState());
//        System.out.println(thread1.getState());

    }

    @Override
    public void run() {
        synchronized (resource) {
            System.out.println(Thread.currentThread().getName() + " got the resource lock");
            try {
                System.out.println(Thread.currentThread().getName() + " wait to start");
                resource.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " go to the end");
        }
    }
}
