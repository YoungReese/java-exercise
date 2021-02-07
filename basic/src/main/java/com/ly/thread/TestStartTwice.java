package com.ly.thread;

/**
 * liyang 2020-12-08
 * 线程无法启动两次，也就是无法从terminated回到new
 *
 * start()源码解析
 * 1、启动新线程检查线程状态
 * 2、加入线程组
 * 3、调用start0()
 *
 * 结果：
 * Exception in thread "main" java.lang.IllegalThreadStateException
 * 	at java.lang.Thread.start(Thread.java:708)
 * 	at com.ly.thread.TestStartTwice.main(TestStartTwice.java:12)
 * hello
 *
 * Process finished with exit code 1
 */
public class TestStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("hello"));
        thread.start();
        thread.start();
    }
}
