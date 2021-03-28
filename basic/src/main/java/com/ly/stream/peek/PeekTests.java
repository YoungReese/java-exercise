package com.ly.stream.peek;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * liyang 2021-03-28
 * 使用 peek
 */
public class PeekTests {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("张三", "李四", "王五", "赵六", "陈七");
        stream.peek(peekAndPrintZhangSanOrChenQi).collect(Collectors.toList());
    }

    private static final Consumer<String> peekAndPrintZhangSanOrChenQi = s -> { if ("张三".equals(s) || "陈七".equals(s)) System.out.println(s); };
}
