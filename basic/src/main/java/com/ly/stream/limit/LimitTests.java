package com.ly.stream.limit;

import java.util.ArrayList;
import java.util.List;

/**
 * liyang 2021-03-26
 * 测试 limit
 */
public class LimitTests {
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            numbers.add(i);
        }

        numbers.stream().limit(10).forEach(System.out::println);

    }
}
