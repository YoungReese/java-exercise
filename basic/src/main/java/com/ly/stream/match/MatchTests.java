package com.ly.stream.match;

import java.util.Arrays;

/**
 * liyang 2021-04-01
 *
 * 测试 anyMatch、allMatch、noneMatch => 都具有短路特性
 */
public class MatchTests {
    public static void main(String[] args) {

        /**
         * 测试 anyMatch
         * boolean anyMatch(IntPredicate predicate);
         */
        boolean isExistFive = Arrays.stream(new int[]{1, 1, 2, 3, 3, 4, 5}).distinct().anyMatch((num) -> num == 5);
        System.out.println("isExistFive: " + isExistFive);

        /**
         * 测试 allMatch
         * boolean allMatch(IntPredicate predicate);
         */
        boolean isAllPositive = Arrays.stream(new int[]{1, 1, 2, 3, 3, 4, 5}).distinct().allMatch((num) -> num > 0);
        System.out.println("isAllPositive: " + isAllPositive);

        /**
         * 测试 noneMatch
         * boolean noneMatch(IntPredicate predicate);
         */
        boolean isExistNegative = Arrays.stream(new int[]{1, 1, 2, 3, 3, 4, 5}).distinct().noneMatch((num) -> num >= 0);
        System.out.println("isExistNegative: " + isExistNegative);

    }
}
