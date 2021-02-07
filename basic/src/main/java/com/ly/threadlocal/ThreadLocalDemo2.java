package com.ly.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * liyang 2021-01-20
 * SimpleDateFormat.parse方法会有线程安全的问题,我们可以尝试使用ThreadLocal包装
 *
 * 在上面我们说过threadLocal有可能存在内存泄漏，在使用完之后，
 * 最好使用remove方法将这个变量移除，就像在使用数据库连接一样，及时关闭连接。
 */
public class ThreadLocalDemo2 {

    private static ThreadLocal<SimpleDateFormat> tlSdf = new ThreadLocal<>();

    private static class DateUtil implements Runnable {
        private String date; public DateUtil(String date) {
            this.date = date;
        }
        @Override
        public void run() {
            if (tlSdf.get() == null) {
                tlSdf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            } else {
                try {
                    Date date = tlSdf.get().parse(this.date);
                    System.out.println(date);
                    tlSdf.remove(); // runnable将在这里使用完毕，因此可以在这里remove，避免内存泄漏
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new DateUtil("2021-01-20 19:00:" + i % 60));
        }
        executorService.shutdown();
    }

}
