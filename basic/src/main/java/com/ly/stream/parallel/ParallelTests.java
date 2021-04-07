package com.ly.stream.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * liyang 2021-4-07
 *
 */
public class ParallelTests {

    public static void main(String[] args) {
        /**
         * 验证求和正确性
         */
        long sum = parallelSum(100);
        System.out.println(sum);
        sum = sequentialSum(100);
        System.out.println(sum);
        sum = iterativeSum(100);
        System.out.println(sum);

        /**
         * measureSumPerf 传入的参数有个函数式接口 Function，
         * 可以如下传入，也可以使用符合该函数式接口的函数引用
         */
        measureSumPerf((n) -> {
            long total = 0;
            for (int i = 1; i <= n; i++) {
                total += i;
            }
            return total;
        }, 1000);

        /**
         * 测试3种方式的性能
         *
         * Iterative sum done in: 3 msecs
         * Sequential sum done in: 103 msecs
         * Parallel sum done in: 148 msecs
         *
         * 这相当令人失望，求和方法的并行版本比顺序版本要慢很多。你如何解释这个意外的结果 呢?这里实际上有两个问题:
         * 1、iterate生成的是装􏱒的对象，必须拆箱成数字才能求和;
         * 2、我们很难把iterate分成多个独立块来并行执行。
         */
        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelTests::iterativeSum, 10_000_000) + " msecs");
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelTests::sequentialSum, 10_000_000) + " msecs");
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelTests::parallelSum, 10_000_000) + " msecs");

        /**
         * 测试使用 rangeClosed 替代 iterate
         *
         * 串行流下的性能：RangeClosed sequential sum done in: 5 msecs
         * 并行流下的性能：RangeClosed parallel sum done in: 1 msecs
         *
         * 这里的测试表明，使用正确的数据结构然后使其并行工作能够保证最佳的性能
         */
        System.out.println("RangeClosed sequential sum done in: " + measureSumPerf(ParallelTests::rangedSum, 10_000_000) + " msecs");
        System.out.println("RangeClosed parallel sum done in: " + measureSumPerf(ParallelTests::parallelRangedSum, 10_000_000) + " msecs");
    }




    /**
     * 测量对前n个自然数求和的函数的性能
     */
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }


    /**
     * stream => 并行方式求值
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * stream => 串行方式求值
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }


    /**
     * 传统 Java 循环方式
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }


    /**
     * 使用 LongStream 避免装拆箱、rangeClosed 生成范围内的自然数，容易拆分为独立的小块
     * 串行
     */
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    /**
     * 使用 rangeClosed
     * 并行
     */
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

}
