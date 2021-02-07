package com.ly.thread.commom;

/**
 * liyang 2020-12-10
 * 测试主线程join()后的状态：WAITING
 *
 * 结果：
 * 主线程等待子线程运行完毕
 * 主线程状态：WAITING
 * Thread-0 子线程运行结束
 * main 主线程运行结束
 *
 * Process finished with exit code 0
 */
public class TestJoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("主线程状态：" + mainThread.getState()); // WAITING
                    System.out.println(Thread.currentThread().getName() + " 子线程运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("主线程等待子线程运行完毕");
        thread.join();
        System.out.println(Thread.currentThread().getName() + " 主线程运行结束");
    }

}
