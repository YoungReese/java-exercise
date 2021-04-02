package com.ly.stream.range;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * liyang 2021-04-02
 * <p>
 * 生成勾股数
 */
public class GenerateThreeNumTests {
    public static void main(String[] args) {

        /**
         * 方式1
         */
        Stream<int[]> pythagoreanTriples01 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );

        pythagoreanTriples01.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        System.out.println("--------------");

        /**
         * 方式2
         */
        Stream<int[]> pythagoreanTriples02 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .boxed()
                                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );

        pythagoreanTriples02.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        System.out.println("--------------");

        /**
         * 方式3
         */
        Stream<double[]> pythagoreanTriples03 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0));

        pythagoreanTriples03.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }
}
