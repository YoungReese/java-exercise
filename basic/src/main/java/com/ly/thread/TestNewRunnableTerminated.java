package com.ly.thread;

/**
 * liyang 2020-12-09
 * 测试线程NEW、RUNNABLE、TERMINATED状态的转换
 * java的线程状态RUNNABLE对应os中的READY和RUNNING状态
 * java一旦从NEW经过.start()就进入RUNNABLE状态，表示在JVM中已处于运行状态，但对于是否在os上运行取决于os是否分配了资源！
 * 源码注释：
 * Thread state for a runnable thread.
 * A thread in the runnable state is executing in the Java virtual machine
 * but it may be waiting for other resources from the operating system such as processor.
 *
 * 结果：
 * NEW
 * RUNNABLE
 * run_start: sum = 2020
 * RUNNABLE
 * run_end:   sum = 2020
 * TERMINATED
 *
 * Process finished with exit code 0
 */
public class TestNewRunnableTerminated implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new TestNewRunnableTerminated());
        // 打印出NEW的状态
        System.out.println(thread.getState()); // NEW
        thread.start();
        System.out.println(thread.getState()); // RUNNABLE
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出RUNNABLE的状态，即使是正在运行，也是RUNNABLE，而不是RUNNING
        System.out.println(thread.getState()); // RUNNABLE（注：如果run()中的任务很快就结束，这里会显示TERMINATED）
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出TERMINATED状态
        System.out.println(thread.getState()); // TERMINATED（注：如果run()中的任务还没结束，这里会显示RUNNABLE）
    }

    @Override
    public void run() {
        int sum = 2020;
        System.out.println("run_start: sum = " + sum);
        // 模拟耗时计算
        for (int cnt = 0; cnt < 100000; cnt++) {
            for (int i = 0; i <= 1000; i++) {
                sum += i;
            }
            for (int i = 0; i <= 1000; i++) {
                sum -= i;
            }
        }
        System.out.println("run_end:   sum = " + sum);
    }
}
