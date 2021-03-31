package com.ly.stream.find;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * liyang 2021-04-01
 *
 * 测试 findAny、findFirst => 都具有短路特性
 *
 * 为什么会同时有 findFirst 和 findAny 呢?
 * 答案是并行。
 * 找到到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流时限制较少。
 */
public class FindTests {
    public static void main(String[] args) {

        /**
         * 测试 findAny
         * OptionalInt findAny(); // 这里 OptionalInt 是被推导出来的
         *
         * Returns an {@link OptionalInt} describing some element of the stream, or
         * an empty {@code OptionalInt} if the stream is empty.
         */
        OptionalInt anyBiggerThanThree = Arrays.stream(new int[]{1, 1, 2, 3, 3, 4, 5}).distinct().filter((num) -> num >= 3).findAny();
        System.out.println("查找任何一个大于等于3的数字，没有返回-1: " + anyBiggerThanThree.orElse(-1));

        /**
         * Optional<T> findAny();
         *
         * Returns an {@link Optional} describing some element of the stream, or an
         * empty {@code Optional} if the stream is empty.
         */
        Optional<String> anyWordHasLengthFive = Arrays.stream(new String[]{"hello", "world", "stream", "function", "find", "lambda"}).distinct().filter((word) -> word.length() == 5).findAny();
        System.out.println("查找任何一个长度等于5的单词，没有返回null: " + anyWordHasLengthFive.orElse("null"));

        /**
         * Optional<T> findFirst();
         *
         * Returns an {@link Optional} describing the first element of this stream,
         * or an empty {@code Optional} if the stream is empty.  If the stream has
         * no encounter order, then any element may be returned.
         */
        Optional<String> firstWordHasLengthSix = Arrays.stream(new String[]{"hello", "world", "stream", "function", "find", "lambda"}).distinct().filter((word) -> word.length() == 6).findFirst();
        System.out.println("查找第一个长度等于6的单词，没有返回null（注：在流有顺序的情况下此查找才有意义) ：" + firstWordHasLengthSix.orElse("null"));

    }
}
