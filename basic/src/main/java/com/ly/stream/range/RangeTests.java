package com.ly.stream.range;

import java.util.stream.IntStream;

/**
 * liyang 2021-04-01
 *
 * 测试 range(1, 10)         =>   [1, 10)
 *     rangeClosed(1, 100)  =>   [1, 10]
 */
public class RangeTests {
    public static void main(String[] args) {

        /**
         * public static IntStream range(int startInclusive, int endExclusive)
         *
         * Returns a sequential ordered {@code IntStream} from {@code startInclusive}
         * (inclusive) to {@code endExclusive} (exclusive) by an incremental step of
         * {@code 1}.
         */
        IntStream intStream1 = IntStream.range(1, 10).filter(x -> (x & 1) == 0);
        System.out.println("打印 [1, 10) 中的偶数：");
        intStream1.forEach(System.out::println);

        /**
         * public static IntStream rangeClosed(int startInclusive, int endInclusive)
         *
         * Returns a sequential ordered {@code IntStream} from {@code startInclusive}
         * (inclusive) to {@code endInclusive} (inclusive) by an incremental step of
         * {@code 1}.
         */
        IntStream intStream2 = IntStream.rangeClosed(1, 10).filter(x -> (x & 1) == 0);
        System.out.println("打印 [1, 10] 中的偶数：");
        intStream2.forEach(System.out::println);

    }
}
