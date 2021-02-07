package com.ly.test;

/**
 * liyang 2020-11-30
 * 当阻塞队列为空，使用take取元素会被阻塞
 * 需要外部中断跳出阻塞，直接进入finally并执行后续操作
 *
 *
 * 1、注释thread.interrupt();
 * 结果：
 * 当前子线程被阻塞
 *
 * 2、开启thread.interrupt();
 * 结果：
 * 当前子线程被阻塞
 * 中断消除阻塞
 * 跳出try块
 * 子线程正常执行完毕，主线程执行且正常结束！
 *
 * Process finished with exit code 0
 */

import java.util.concurrent.LinkedBlockingQueue;

public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
                try {
                    System.out.println("当前子线程被阻塞");
                    String e =  queue.take();
                    System.out.println("这一句会被执行吗？"); // 阻塞被中断后不会执行这一句
                } catch (InterruptedException retry) {
                    System.out.println("中断消除阻塞");
                }
                System.out.println("跳出try块");
            }
        });
        thread.start();

        thread.interrupt(); // 给子线程一个中断

        thread.join();
        System.out.println("子线程正常执行完毕，主线程执行且正常结束！");
    }
}
