package com.ly.stream.chap11;

import java.util.concurrent.TimeUnit;

/**
 * liyang 2021-04-13
 *
 * 1秒延迟，模拟数据库查询等耗时操作
 */
public class DelayUtils {
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
