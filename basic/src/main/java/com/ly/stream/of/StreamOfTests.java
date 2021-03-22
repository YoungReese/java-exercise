package com.ly.stream.of;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * liyang 2021-03-22
 *
 * 数组元素是基本类型：转换成流需要使用 Arrays.stream(arr)
 *
 * 数组元素不是基本类型：可以使用 Stream.of(array) 或者 Arrays.stream(array)
 *
 */
public class StreamOfTests {

    public static void main(String[] args) {
        /**
         * 数组元素类型是 int
         */
        int[] arr = { 1, 3, 5, 7, 9};
        IntStream stream = Arrays.stream(arr);
        Integer[] integers = stream.filter(x -> x != 5).boxed().toArray(Integer[]::new);
        for (Integer integer: integers) System.out.println(integer);

        /**
         * 数字元素类型是 Integer
         */
        Integer[] array= { 1, 3, 5, 7, 9};
        System.out.println(Stream.of(array).filter(x -> x > 5).collect(Collectors.toList()));
        Arrays.stream(array).forEach(System.out::print);

    }

}
