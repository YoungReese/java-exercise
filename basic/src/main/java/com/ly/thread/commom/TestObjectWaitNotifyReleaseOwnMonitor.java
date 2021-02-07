package com.ly.thread.commom;

/**
 * liyang 2020-12-09
 * 证明wait只释放当前的那把锁
 *
 * 结果：
 * Thread-0 got resource01 lock
 * Thread-0 got resource02 lock
 * Thread-0 releases resource01 lock
 * Thread-1 got resource01 lock
 * Thread-1 try to get resource02 lock
 */
public class TestObjectWaitNotifyReleaseOwnMonitor {
    private static final Object resource01 = new Object();
    private static final Object resource02 = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(() -> {
            synchronized (resource01) {
                System.out.println(Thread.currentThread().getName() + " got resource01 lock");
                synchronized (resource02) {
                    System.out.println(Thread.currentThread().getName() + " got resource02 lock");
                    try {
                        System.out.println(Thread.currentThread().getName() + " releases resource01 lock");
                        resource01.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resource01) {
                System.out.println(Thread.currentThread().getName() + " got resource01 lock");
                System.out.println(Thread.currentThread().getName() + " try to get resource02 lock");
                synchronized (resource02) {
                    System.out.println(Thread.currentThread().getName() +" got resource02 lock");
                }
            }
        });

        thread0.start();
        thread1.start();

    }
}
