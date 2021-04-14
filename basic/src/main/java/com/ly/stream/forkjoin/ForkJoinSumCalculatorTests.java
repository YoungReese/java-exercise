package com.ly.stream.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;

import static com.ly.stream.parallel.ParallelTests.measureSumPerf;

/**
 * liyang 2021-04-07
 *
 * 测试自己实现的
 */
public class ForkJoinSumCalculatorTests {
    public static void main(String[] args) {

        /**
         * 获取处理器内核个数
         */
        System.out.println("This machine's available processors: " + Runtime.getRuntime().availableProcessors());


        /**
         * 1000000000L => Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         * so maximum n is 100_000_000L in this machine
         *
         * when n is 10_000_000L, the forkJoinSum done in 61 msecs.
         * Q: 这个性能看起来比用并行流的版本要差 ?
         * A: 但这只是因为必须先要把整个数字流都放进一个 long[]，之后才能在ForkJoinSumCalculator任务中使用它。
         */
        System.out.println("The forkJoinSum done in: " + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " msecs");


        /**
         * 提前处理好 long[]，在进行计算
         * 传入的 n 不使用，仅仅为了兼容之前的接口
         *
         * The second forkJoinSum done in: 3 msecs
         * 证明了 toArray() => long[] spend a lot
         */
        System.out.println("The second forkJoinSum done in: " + measureSumPerf(adder, 10_000_000L) + " msecs");

    }

    private static long n = 10_000_000L;
    private static long[] numbers = LongStream.rangeClosed(1, n).toArray();
    private static Function<Long, Long> adder = (n) -> { // 传入的 n 不使用，仅仅为了兼容之前的接口
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task); // Performs the given task, returning its result upon completion.
    };
}
