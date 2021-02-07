package com.ly.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * liyang 2020-12-12
 * 演示重排序现象
 * 对于小概率事件，可以重复执行多次来复现
 *
 * 1、one线程先运行，two线程后运行（x = 0, y = 1）
 * 2、two线程先运行，one线程后运行（x = 0, y = 1）
 * 3、one线程运行a = 1，切换到two运行b = 1（x = 1，y = 1）
 * 4、那如果发生指令重排序，因为对于one线程a = 1，x = b，jmm保证单线程结果一致，但可能发生指令重排，
 *   也就是先执行x = b，后执行a = 1，two线程同理分析，最后导致最后（x = 0, y = 0）
 *
 * 结果：
 * 第8265次（x = 0, y = 1)
 * 第8266次（x = 0, y = 1)
 * 第8267次（x = 1, y = 0)
 * 第8268次（x = 1, y = 1)
 * ...
 * 第30356次（x = 1, y = 0)
 * 第30357次（x = 0, y = 0)
 *
 * Process finished with exit code 0
 *
 * 出现了（x = 0, y = 0)，证明了jmm存在指令重排序
 */

public class TestOutOfOrderExecute {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(3);

            Thread one = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread two = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            one.start();
            two.start();

            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i + "次（x = " + x + ", y = " + y + ")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }

}
