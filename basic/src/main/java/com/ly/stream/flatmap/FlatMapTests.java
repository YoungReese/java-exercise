package com.ly.stream.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * liyang 2021-03-31
 *
 * 测试 flatMap
 */
public class FlatMapTests {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("world");
        /**
         * flatMap(Arrays::stream) => 将各个生成的扁平流化为单个流
         * 一言以蔽之，flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接 起来成为一个流。
         */
        List<String> collect = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
    }
}
