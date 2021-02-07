package com.ly.thread.commom;

/**
 * liyang 2020-12-09
 * 测试Object中的wait方法：public final void wait() throws InterruptedException { wait(0); }
 *
 * 1、研究代码的执行顺序
 * 2、证明wait()方法会释放锁
 *
 * 因为wait会释放synchronized锁，所以它的调用必须放在synchronized同步代码块中或其锁修饰的函数中
 *
 * 结果：
 * Thread-0开始执行了
 * Thread-1调用了notify()
 * Thread-0获取到了锁
 *
 * Process finished with exit code 0
 */
public class TestObjectWait {
    public static final Object object = new Object();

    static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify(); // 通知其他等待此锁的线程准备拿锁，但是得等到走出同步代码块才会释放锁
                System.out.println(Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadA thread0 = new ThreadA();
        ThreadB thread1 = new ThreadB();
        thread0.start();
        Thread.sleep(200);
        thread1.start();
//        object.wait();
    }
}
