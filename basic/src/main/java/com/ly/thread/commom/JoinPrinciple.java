package com.ly.thread.commom;

/**
 * liyang 2020-12-10
 * 分析join()内部原理，自己实现一个join()
 *
 * join()内部通过一个同步方法调用wait(0)，因此会持有这个thread对象的monitor，
 * 并且线程结束的时候会自动调用notifyAll释放这个线程对象的monitor
 *
 * join() -> public final void join() throws InterruptedException
 *        -> public final synchronized void join(long millis)
 *        -> wait(0)
 *
 * wait(0): The specified amount of real time has elapsed, more or less.
 * If timeout is zero, however, then real time is not taken into consideration
 * and the thread simply waits until notified.
 *
 * 结果：
 * main 开始等待子线程运行完毕
 * Thread-0 执行完毕！
 * main 主线程运行结束
 *
 * Process finished with exit code 0
 *
 *
 * 注：
 * 如果打开Thread.sleep(2000)和thread.join()的等价方法，并且等价方法中不加if (thread.isAlive())，
 * 直接调用wait，会导致程序无法正常结束，原因分析见注释即可！
 *
 */

public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 执行完毕！");
            }
        });

        thread.start();
        System.out.println(Thread.currentThread().getName() + " 开始等待子线程运行完毕");
//        Thread.sleep(2000);   // 模拟子线程已经死亡，这个时候，贸然使用wait方法会出错，因此模仿join方法中先判断是否存活！
//        thread.join();
        synchronized (thread) {     // thread.join()的等价方法
            if (thread.isAlive()) { // 如果子线程已死，这个时候使用wait就没有线程死亡时候的notify了，因此需要先判断是否存活！
                thread.wait();      // wait()会调用wait(0)
            }
        }
        System.out.println(Thread.currentThread().getName() + " 主线程运行结束");
    }
}
