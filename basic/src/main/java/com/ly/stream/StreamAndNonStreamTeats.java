package com.ly.stream;

import java.util.Arrays;
import java.util.List;

import static com.ly.stream.NonStreamTests.testNotUsingStream;
import static com.ly.stream.StreamTests.testUsingStream;

/**
 * liyang 2021-03-15
 * 对比测试 non_stream 和 stream 方式
 *
 * 参考资料：
 * https://www.runoob.com/java/java8-streams.html
 * https://www.cnblogs.com/yulinfeng/p/12561664.html
 */
public class StreamAndNonStreamTeats {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

        testUsingStream(strings, numbers, integers);

        System.out.println(" ");

        testNotUsingStream(strings, numbers, integers);
    }

}
