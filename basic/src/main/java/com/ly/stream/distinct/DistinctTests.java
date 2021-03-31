package com.ly.stream.distinct;

import java.util.Arrays;

/**
 * liyang 2021-03-31
 *
 * 去重 => distinct
 */
public class DistinctTests {
    public static void main(String[] args) {
        Arrays.stream(new int[]{1, 1, 2, 3, 3, 4, 5}).distinct().forEach(System.out::println);
    }
}
