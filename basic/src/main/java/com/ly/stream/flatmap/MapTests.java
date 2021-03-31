package com.ly.stream.flatmap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * liyang 2021-03-31
 *
 * 测试 map 展示 map 无法展平映射关系的情况
 *
 *
 * word.split("");  => 这将按每个字符将其拆分（但是创建了副本 => String[]）
 * String[] split = "Hello".split("");
 */
public class MapTests {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("world");
        /**
         * 可以看到，最终转换成了 List<String[]> 而不是 List<String>
         * 想要后者就得使用 flatMap
         */
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        for (String[] strings : collect) {
            for (String s : strings) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
