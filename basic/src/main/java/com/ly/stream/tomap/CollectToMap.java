package com.ly.stream.tomap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * liyang 2021-03-28
 * 将学生 list<Student> 转换成 map<String, Student>
 */
public class CollectToMap {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("20210328", "liyang"));
        students.add(new Student("20210329", "young"));

        Map<String, Student> map = students.stream().collect(Collectors.toMap(Student::getStudentID, student -> student));
        System.out.println(map.get("20210328").getName());

        /**
         * map 不能直接转换成 stream，需要转换成 set 后操作
         */
        map.entrySet().stream().forEach(System.out::println);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private  static class Student {
        private String studentID;
        private String name;
    }

}
