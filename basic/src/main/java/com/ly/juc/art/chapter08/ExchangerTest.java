package com.ly.juc.art.chapter08;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * liyang 2021-01-04
 * 模拟两个人录入数据，录完进行比对录入数据是否相同
 *
 * 结果：
 * Data from A is equals data from B ? false
 * A录入的数据: 银行流水A
 * B录入的数据: 银行流水B
 *
 * Process finished with exit code 0
 */
public class ExchangerTest {

    private static final Exchanger<String> exchanger = new Exchanger<String>();
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(() -> {
            try {
                String A = "银行流水A"; // A录入的银行流水
                exchanger.exchange(A);
            } catch (InterruptedException ignored) {
            }
        });

        threadPool.execute(() -> {
            try {
                String B = "银行流水B"; // B录入的银行流水
                String A = exchanger.exchange("B");
                System.out.println("Data from A is equals data from B ? " + A.equals(B) + "\nA录入的数据: " + A + "\nB录入的数据: " + B);
            } catch (InterruptedException ignored) {
            }
        });

        threadPool.shutdown();
    }
}
