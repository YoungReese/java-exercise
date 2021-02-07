package com.ly.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * liyang 2020-12-13
 * 测试不适合volatile的场合（因为volatile仅仅保证可见性，不保证a++操作的原子性！）
 *
 * 结果：
 * 18437
 * 20000
 *
 * Process finished with exit code 0
 */
public class TestVolatileNotCorrectForOnePlusPlus {

    volatile static int a = 0;
    static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                a++;
                ai.getAndIncrement();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(a);
        System.out.println(ai.get());
    }

}
