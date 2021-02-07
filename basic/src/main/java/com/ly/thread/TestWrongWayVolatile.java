package com.ly.thread;

/**
 * liyang 2020-12-09
 * 测试错误停止线程的方式：使用volatile，此种场景可以，但不推荐！
 *
 * 结果：
 * ...
 * 3600是100的倍数。
 *
 * Process finished with exit code 0
 */
public class TestWrongWayVolatile implements Runnable {
    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestWrongWayVolatile r = new TestWrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        r.canceled = true;
    }
}
