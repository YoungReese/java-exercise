package com.ly.stream.infinitestream.iterate;

import java.util.stream.Stream;

/**
 * liyang 2021-04-04
 *
 * 使用 iterator 创建无限流
 */
public class IterateTests {
    public static void main(String[] args) {

        /**
         * public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
         *
         * Returns an infinite sequential ordered {@code Stream} produced by iterative
         * application of a function {@code f} to an initial element {@code seed},
         * producing a {@code Stream} consisting of {@code seed}, {@code f(seed)},
         * {@code f(f(seed))}, etc.
         *
         * 生成5个最小的正偶数：2、4、6、8、10
         */
        Stream.iterate(2, n -> n + 2).limit(5).forEach(System.out::println);


        /**
         * 生成10个斐波拉契元组序列
         */
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10).forEach(t -> System.out.println("(" + t[0] + ", " + t[1] +")"));


        /**
         * 生成10个斐波拉契数
         */
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10).forEach(t -> System.out.print(t[0] + " "));
    }
}

