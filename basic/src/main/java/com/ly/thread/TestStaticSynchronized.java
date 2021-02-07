package com.ly.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * liyang 2020-11-24
 * 使用synchronize对静态方法和非静态（普通）方法加锁
 * 静态方法锁的是synchronized(*.class)
 * 非静态方法锁的是synchronized(this)
 *
 * 所以二者可以并行执行
 * 但是此时有个普通静态变量，两个函数都可以修改，即并行修改，不安全
 *
 * 结果：
 * Thread-1非静态同步方法开始执行
 * Thread-0静态同步方法开始执行
 * Thread-1非静态同步方法结束执行
 * Thread-0静态同步方法结束执行
 * 187676
 * 200000
 * 181583
 *
 * Process finished with exit code 0
 *
 * static int a = 0; // 线程不安全
 * static AtomicInteger b = new AtomicInteger(); // 线程安全
 * static volatile int c = 0; // 线程不安全
 *
 * 关于volatile为啥不保证线程安全？
 * 其语义：1、保证可见性 2、禁止指令重排，但是普通变量可能会在assign,store,write之间插入其他操作，
 * 导致更改后的数据无法马上同步回主存，其他线程读取的可能是过期的旧数据。
 */
public class TestStaticSynchronized implements Runnable {

    static TestStaticSynchronized instance = new TestStaticSynchronized();

    static int a = 0;

    static AtomicInteger b = new AtomicInteger();

    static volatile int c = 0;


    public synchronized static void method1() {
        System.out.println(Thread.currentThread().getName() + "静态同步方法开始执行");
        try {
            for (int j = 0; j < 100000; j++) {
                a++;
                b.getAndIncrement();
                c++;
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "静态同步方法结束执行");
    }


    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "非静态同步方法开始执行");
        try {
            for (int j = 0; j < 100000; j++) {
                a++;
                b.getAndIncrement();
                c++;
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "非静态同步方法结束执行");
    }

    @Override
    public void run() {
        if ("Thread-0".equals(Thread.currentThread().getName())) {
            method1();
        } else {
            method2();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a);
        System.out.println(b.get());
        System.out.println(c);
    }



}
