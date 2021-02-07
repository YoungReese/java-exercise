package com.ly.thread.commom;

/**
 * liyang 2020-12-09
 * 两个线程交替打印0~100的奇偶数，使用synchronized和wait/notify实现
 *
 * 结果：
 * 奇数:99
 * 偶数:100
 *
 * Process finished with exit code 0
 */
public class WaitNotifyPrintOddEven {

    private static volatile int i = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread0 = new Thread(new PrintTask(), "偶数");
        Thread thread1 = new Thread(new PrintTask(), "奇数");
        thread0.start();
        Thread.sleep(10);
        thread1.start();


//        // 不加break，打开这段代码会验证break后面的注释
//        Thread.sleep(100);
//        System.out.println("Thread0_偶数线程存活？ " + thread0.isAlive()); // true
//        System.out.println("Thread1_奇数线程存活？ " + thread1.isAlive()); // false
    }

    static class PrintTask implements Runnable {
        @Override
        public void run() {
            while(i <= 100) {
                synchronized (lock) {
                    // 拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ":" + (i++));
                    lock.notify();
                    try {
                        if (i > 100) break; // 因为最后一个偶数线程wait不会被唤醒，永久在此等待，所以使用break在wait前跳出
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
