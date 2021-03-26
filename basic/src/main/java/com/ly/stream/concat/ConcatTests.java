package com.ly.stream.concat;

import java.util.stream.Stream;

/**
 * liyang 2021-03-26
 * 使用 concat
 */
public class ConcatTests {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("张三", "李四", "王五");
        Stream<String> stream2 = Stream.of("赵六", "陈七");

        /**
         * public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
         *
         * Creates a lazily concatenated stream whose elements are all the
         * elements of the first stream followed by all the elements of the
         * second stream.  The resulting stream is ordered if both
         * of the input streams are ordered, and parallel if either of the input
         * streams is parallel.  When the resulting stream is closed, the close
         * handlers for both input streams are invoked.
         */
        Stream.concat(stream1, stream2).forEach(System.out::println);
    }
}
