package com.ly.thread.commom;

/**
 * liyang 2020-12-09
 * 两个线程交替打印0~100的奇偶数，使用synchronized实现
 */
public class SyncPrintOddEven {

    private static volatile int i = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            while (i <= 100) {
                synchronized (lock) {
                    if ((i & 1) == 0 && i <= 100) {
                        System.out.println(Thread.currentThread().getName() + ": " + (i++));
                    }
                }
            }
        }, "偶数").start();

        new Thread(() -> {
            while (i <= 100) {
                synchronized (lock) {
                    if ((i & 1) == 1 && i <= 100) {
                        System.out.println(Thread.currentThread().getName() + ": " + (i++));
                    }
                }
            }
        }, "奇数").start();

    }

}


//public class SyncPrintOddEven {
//    public static void main(String[] args) {
//
//    }
//
//    static class PrintOdd implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 1; i <= 100; i += 2) {
//                printOdd(i);
//            }
//        }
//    }
//
//    static class PrintEven implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 0; i <= 100; i += 2) {
//                printEven(i);
//            }
//        }
//    }
//
//    public synchronized void printOdd(int i) {
//        try {
//            wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(i);
//    }
//
//    public synchronized void printEven(int i) {
//        try {
//            wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(i);
//    }
//}
//
