package com.ly.thread.commom;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * liyang 2020-12-10
 * 让线程睡眠的两种方法，本质一样，但实际开发推荐第二种，因为长时间休眠不需要转换成毫秒，更加的优雅！
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()
 *
 * TimeUnit中sleep源码：
 * public void sleep(long timeout) throws InterruptedException {
 *     if (timeout > 0) {
 *         long ms = toMillis(timeout);
 *         int ns = excessNanos(timeout, ms);
 *         Thread.sleep(ms, ns);
 *     }
 * }
 */
public class TestThreadSleepInterrupted implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TestThreadSleepInterrupted());
        thread.start();
        System.out.println(thread.getState());

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(thread.getState());

        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
//                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
                System.out.println(new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
