package com.ly.stream.countword;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * liyang 2021-04-02
 *
 * 统计出文本中有多少个不同的单词（不区分大小写） // .map(String::toUpperCase)
 *
 * 参考资料：https://www.cnblogs.com/pinlantu/p/10125236.html
 */
public class CountWordTests {
    public static void main(String[] args) {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("/Users/11117846/Desktop/current/sample.txt"), Charset.defaultCharset())) {
            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .flatMap(word -> Arrays.stream(word.split(",")))
                    .flatMap(word -> Arrays.stream(word.split("\\."))) // .是正则保留字段，需要\\转译
                    .map(String::toUpperCase)
                    .distinct()
                    .count();
        } catch (IOException e) {
            System.out.println("CountWordTests => " + e.getMessage());
        }
        System.out.println("共计多少个不同的单词（不区分大小写）：" + uniqueWords);

        /**
         * 打印分割出来的单词
         */
        try (Stream<String> lines = Files.lines(Paths.get("/Users/11117846/Desktop/current/sample.txt"), Charset.defaultCharset())) {
            lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .flatMap(word -> Arrays.stream(word.split(",")))
                    .flatMap(word -> Arrays.stream(word.split("\\.")))
                    .map(String::toUpperCase)
                    .distinct()
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("CountWordTests => " + e.getMessage());
        }

        System.out.println("------");

        /**
         * 使用正则表达式分割
         * split 应该是根据文本定制的 regex
         */
        String regex = ",| |\\."; // 按照 "," or " " or "." 将文本分割
        try (Stream<String> lines = Files.lines(Paths.get("/Users/11117846/Desktop/current/sample.txt"), Charset.defaultCharset())) {
            lines.flatMap(line -> Arrays.stream(line.split(regex)))
                    .filter(word -> !word.isEmpty())
                    .map(String::toUpperCase)
                    .distinct()
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("CountWordTests => " + e.getMessage());
        }
    }
}
