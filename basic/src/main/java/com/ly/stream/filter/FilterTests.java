package com.ly.stream.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * liyang 2021-03-15
 * 过滤分数大于等于60分的同学
 */
public class FilterTests {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(50.0));
        students.add(new Student(60.0));
        students.add(new Student(100.0));
        System.out.println(fetchPassedStudentsByStreamFilter(students).toString());
    }

    public static List<Student> fetchPassedStudentsByStreamFilter(List<Student> students) {
        return students.stream()
                .filter(student -> student.getScore().compareTo(60.0) >= 0)
                .collect(Collectors.toList());
    }

    private static class Student {
        private Double score;

        public Student() {}

        public Student(Double score) {
            this.score = score;
        }

        public Double getScore() {
            return this.score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "score=" + score +
                    '}';
        }
    }

}
