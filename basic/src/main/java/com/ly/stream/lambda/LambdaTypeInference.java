package com.ly.stream.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * liyang 2021-03-19
 * lambda 的类型推导过程
 */
public class LambdaTypeInference {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student(17, "yx"));
        students.add(new Student(18, "ly"));
        students.add(new Student(19, "sy"));
        students.add(new Student(20, "py"));

        /**
         * 类型推断
         * students 的类型是 List<Student>，因此 student 的类型可以推导出是 Student
         * 因为 getName 的值是 String 类型，所以 map 的返回值可以推导出是 Stream<String>
         *
         * 大多数情况下，编译器都能解析出正确的类型，如果碰到无法解析的情况，添加类型即可
         *
         * 假设这里返回值类型 String 推导不出来，可以使用
         * Stream<String> stringStream = students.stream().<String>map(student -> student.getName());
         */
//        Stream<String> stringStream = students.stream().map(student -> student.getName());
        Stream<String> stringStream = students.stream().<String>map(student -> student.getName());
        stringStream.forEach(System.out::println);


        /**
         * 使用方法引用简化
         * 使用 "-" 合并成一个字符串
         *
         * 例如：
         *     Consumer<String> consumer = (s) -> System.out.println(s);
         * 等同于：
         *     Consumer<String> consumer = System.out::println;
         *
         * 例如：
         *     Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
         * 等同于：
         *     Function<String, Integer> stringToInteger = Integer::parseInt;
         *
         * 例如：
         *     BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
         * 等同于：
         *     BiPredicate<List<String>, String> contains = List::contains;
         *
         * 注意：
         * Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保存一致
         * 若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
         *
         */
        Stream<String> stringStream2 = students.stream().map(Student::getName);
        System.out.println(stringStream2.collect(Collectors.joining("-")));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Student {
        private Integer age;
        private String name;
    }
}
