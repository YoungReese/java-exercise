package com.ly.thread.commom;

/**
 * liyang 2020-12-10
 * 演示sleep期间不释放synchronized的monitor
 */
public class TestThreadSleepDontReleaseMonitor implements Runnable {

    public static void main(String[] args) {
        TestThreadSleepDontReleaseMonitor r = new TestThreadSleepDontReleaseMonitor();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        System.out.println(Thread.currentThread().getName() + " got the monitor");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " exit the synchronized block");
    }
}
