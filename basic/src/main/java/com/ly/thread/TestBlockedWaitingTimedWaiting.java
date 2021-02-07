package com.ly.thread;

/**
 * liyang 2020-12-09
 * 测试BLOCKED、WAITING、TIMED_WAITING
 *
 * 结果：
 * thread1_state: TIMED_WAITING
 * thread2_state: BLOCKED
 * thread1_state: WAITING
 * thread2_state: TIMED_WAITING
 * thread1_state: BLOCKED
 * thread2_state: TIMED_WAITING
 * java.lang.InterruptedException: sleep interrupted
 * 	at java.lang.Thread.sleep(Native Method)
 * 	at com.ly.thread.TestBlockedWaitingTimedWaiting.sync(TestBlockedWaitingTimedWaiting.java:56)
 * 	at com.ly.thread.TestBlockedWaitingTimedWaiting.run(TestBlockedWaitingTimedWaiting.java:52)
 * 	at java.lang.Thread.run(Thread.java:748)
 * java.lang.InterruptedException
 * 	at java.lang.Object.wait(Native Method)
 * 	at java.lang.Object.wait(Object.java:502)
 * 	at com.ly.thread.TestBlockedWaitingTimedWaiting.sync(TestBlockedWaitingTimedWaiting.java:57)
 * 	at com.ly.thread.TestBlockedWaitingTimedWaiting.run(TestBlockedWaitingTimedWaiting.java:52)
 * 	at java.lang.Thread.run(Thread.java:748)
 * thread1_state: TERMINATED
 * thread2_state: TERMINATED
 *
 * Process finished with exit code 0
 */
public class TestBlockedWaitingTimedWaiting implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        TestBlockedWaitingTimedWaiting runnable = new TestBlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出TIMED_WAITING状态，因为正在执行Thread.sleep(1000);
        System.out.println("thread1_state: " + thread1.getState()); // thread1_state: TIMED_WAITING
        //打印出BLOCKED状态，因为thread2想拿得到sync()的锁却拿不到， sleep()方法不会释放锁
        System.out.println("thread2_state: " + thread2.getState()); // thread2_state: BLOCKED

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出WAITING状态，因为执行了wait()
        System.out.println("thread1_state: " + thread1.getState()); // thread1_state: WAITING
        //打印出TIMED_WAITING状态，因为执行了wait()方法会释放锁
        System.out.println("thread2_state: " + thread2.getState()); // thread2_state: TIMED_WAITING


        thread1.interrupt();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // wait()释放锁之后，处理中断的时候会重新拿锁，因为thread1正在执行sleep(1000)，所以暂时拿不到锁就进入BLOCKED
        System.out.println("thread1_state: " + thread1.getState()); // thread1_state: BLOCKED
        System.out.println("thread2_state: " + thread2.getState()); // thread2_state: TIMED_WAITING


        thread2.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread1_state: " + thread1.getState()); // thread1_state: TERMINATED
        System.out.println("thread2_state: " + thread2.getState()); // thread2_state: TERMINATED
    }

    @Override
    public void run() {
        sync();
    }
    private synchronized void sync() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
