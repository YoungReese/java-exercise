package com.ly.thread.commom;

/**
 * liyang 2020-12-10
 * id从1开始，JVM运行起来后，我们自己创建的线程的id早已不是2，因为jvm帮我们创建了其他线程（比如gc线程）
 *
 * // Set thread ID
 * tid = nextThreadID();
 *
 * private static synchronized long nextThreadID() {
 *     return ++threadSeqNumber;  // For generating thread ID 注意：这是先++，因此主线程id是1，而不是0！
 * }
 *
 * 结果：
 * 主线程的ID1
 * 子线程的ID10
 *
 * Process finished with exit code 0
 */
public class TestThreadId {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID" + Thread.currentThread().getId());
        System.out.println("子线程的ID" + thread.getId());
    }
}
