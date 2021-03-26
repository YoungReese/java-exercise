package com.ly.stream.skip;

import java.util.stream.Stream;

/**
 * liyang 2021-03-26
 * 使用 skip
 */
public class SkipTests {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("张三", "李四", "王五", "赵六", "陈七");
        stream.skip(2).limit(2).forEach(System.out::println);
    }
}
