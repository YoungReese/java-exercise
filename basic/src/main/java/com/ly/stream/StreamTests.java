package com.ly.stream;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * liyang 2021-03-15
 * stream 实现方式
 */
public class StreamTests {

    public static void testUsingStream(List<String> strings, List<Integer> numbers, List<Integer> integers) {

        System.out.println("使用 Java 8 stream: ");
        System.out.println("列表: " + strings);

        // 计算空字符串
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        // 计算字符串长度为3的个数
        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        // 删除空字符串
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        // 删除空字符串，并使用"-"把它们合并起来
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining("-"));
        System.out.println("合并字符串: " + mergedString);

        // 获取列表元素平方数并去重
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " + integers);

        // 根据指标获取数组中的数
        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);
    }

}
