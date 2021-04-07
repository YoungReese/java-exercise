package com.ly.stream.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * liyang 2021-04-07
 *
 * 继承RecursiveTask来创建可以用于分支/合并框架的任务
 */
public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {

    /**
     * 要求和的数组
     */
    private final long[] numbers;

    /**
     * 子任务处理数组的起始和终止位置
     */
    private final int start;
    private final int end;

    /**
     * 不再将任务分解为子任务的数组大小
     */
    public static final long THRESHOLD = 10_000;

    /**
     * 公共构造器用于创建主任务
     */
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    /**
     * 对外提供的并行对前 n 个自然数求和
     */
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    /**
     * 私有构造器用于创建子任务
     */
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    /**
     * 覆盖 RecursiveTask 抽象方法 compute
     * @return
     */
    @Override
    protected Long compute() {

        /**
         * 如果求值范围数字小于等于设定的阈值，顺序计算结果
         */
        int length = end - start;
        if (length < THRESHOLD) {
            return computeSequentially();
        }

        /**
         * 创建左子任务 => 计算前一半和
         */
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);

        /**
         * 利用另一个 ForkJoinPool 线程异步执行新创建的子任务
         */
        leftTask.fork();

        /**
         * 创建右子任务 => 计算后一半和
         */
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);

        /**
         * 同步执行第2个子任务，有可能允许进一步递归划分
         */
        Long rightResult = rightTask.compute(); // use rightTask.exec() also fine

        /**
         * 读取第一个子任务的结果，如果尚未完成就等待
         */
        Long leftResult = leftTask.join();

        /**
         * 返回结果
         */
        return leftResult + rightResult;
    }


    /**
     * 顺序计算
     */
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

}
