package com.ly.test;

/**
 * liyang 2020-11-26
 * 模拟cas操作
 *
 * cas操作的缺点：
 * ABA问题
 * 自旋时间过长
 *
 */
public class TestSimulatedCas {
    // volatile加不加都可以，加了能保证可见行，应该会加速value被感知
    // 实际cas实现是需要volatile保证可见行的，我们这里使用synchronized模拟，对value的操作一定是安全的
    private static volatile int value = 0;

    static int failure = 0; // 记录cas失败次数

    // 循环尝试cas操作，使得cas操作一定成功
    public static void getIncrement() {
        while(!compareAndSwap(value, value + 1)) {} // 自旋操作，保证自增一定成功
        // compareAndSwap(value, value + 1); // 自增不一定成功
    }

    // 模拟保证原子性的native方法
    public static synchronized boolean compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
            return true;
        }
        failure++;
        return false;
    }

    // 测试
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    getIncrement();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    getIncrement();
                }
            }
        });

        t1.start();;
        t2.start();
        t1.join();
        t2.join();

        System.out.println(value); // 200000

        System.out.println(failure);
    }

}
