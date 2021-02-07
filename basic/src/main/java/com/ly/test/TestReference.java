package com.ly.test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class TestReference {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        WeakReference<Object> weakRef = new WeakReference<>(obj, refQueue);
        System.out.println(weakRef);
        System.out.println("引用：" + weakRef.get());
        System.out.println("队列中的东西：" + refQueue.poll());
        // 清除强引用, 触发GC
        obj = null;
        System.gc();
        Thread.sleep(200);
        System.out.println(weakRef);
        System.out.println("引用：" + weakRef.get());
        System.out.println("引用加入队列了吗？ " + weakRef.isEnqueued());
        System.out.println("队列中的东西：" + refQueue.poll());
        System.out.println(weakRef);
        System.out.println(weakRef.get());

        /**
         * 输出结果
         * 引用：java.lang.Object@7bb11784
         * 队列中的东西：null
         * 引用：null
         * 引用加入队列了吗？ true
         * 队列中的东西：java.lang.ref.WeakReference@33a10788
         */
    }

}
