package com.ly.thread.commom;

/**
 * liyang 2020-12-10
 * 测试子线程join，主线程被中断，那么主线程不再等待子线程执行完毕
 */
public class TestJoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread1 finished.");
                } catch (InterruptedException e) {
                    System.out.println("子线程中断");
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "主线程中断了");
//            thread1.interrupt();
        }
        System.out.println(Thread.currentThread().getName() +     "已运行完毕");
    }
}
