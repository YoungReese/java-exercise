package com.ly.jmm;

import java.util.concurrent.TimeUnit;

/**
 * liyang 2020-12-12
 * 演示可见性带来的问题
 *
 * 结果：
 * ...
 * b = 2, a = 1
 * b = 2, a = 3
 * b = 3, a = 3
 * ...
 * b = 3, a = 1 // 极少发生，需要运行较长时间才会出现
 * ...
 *
 * 解决方法：加volatile解决 b = 3, a = 1 这种可见性问题
 *
 */
public class TestFiledVisibility {
    int a = 1;  // volatile
    volatile int b = 2;  // volatile

    private void print() {
        System.out.println("b = " + b + ", a = " + a);
    }

    private void change() {
        a = 3;
        b = 4;
    }

    public static void main(String[] args) {
        while (true) {
            TestFiledVisibility o = new TestFiledVisibility();
            new Thread(() ->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.change();
            }).start();
            new Thread(() ->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.print();
            }).start();
        }
    }

}
