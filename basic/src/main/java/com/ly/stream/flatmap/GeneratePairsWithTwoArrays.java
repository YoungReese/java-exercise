package com.ly.stream.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * liyang 2021-03-31
 *
 * 生成排列组合
 */
public class GeneratePairsWithTwoArrays {
    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collect = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        collect.stream().forEach(pair -> System.out.println("[" + pair[0] + ", " + pair[1] + "]"));
    }
}
