package com.ly.thread.threadsafety;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * liyang 2020-12-11
 * 捕捉index++操作出现线程不安全的位置
 *
 * 这个测试案例不是特别好，因为使用了循环栅栏一定程度上协调了两个线程的执行速度，并发冲突次数大大降低！
 *
 * 结果：
 * 发生错误：1157726
 * 发生错误：1590025
 * 发生错误：1643754
 * 发生错误：1663737
 * 发生错误：1953456
 * 发生错误：1970099
 * 发生错误：1991858
 * 表面上结果是：1999990
 * 真正运行的次数：2000000
 * 错误次数：10
 *
 * Process finished with exit code 0
 */
public class MultiThreadError implements Runnable {

    static final MultiThreadError instance = new MultiThreadError();
    int index = 0;
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    final boolean[] marked = new boolean[10000000];

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是：" + instance.index);
        System.out.println("真正运行的次数：" + realIndex.get());
        System.out.println("错误次数：" + wrongCount.get());
    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 1000000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) { // synchronized带来的index可见性 ，因此需要加上&& marked[index - 1]
                    System.out.println("发生错误：" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }

}
