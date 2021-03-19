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
        students.add(new Student(90.0));
        students.add(new Student(100.0));

        System.out.println("通过考试的：" + fetchPassedStudentsByStreamFilter(students).toString());

        System.out.println("每个学生加10分：" + allAddTenPointScore(students));

        System.out.println("分数超过100分的（含）：" + fetchFullMarksStudents(students));
    }

    public static List<Student> fetchPassedStudentsByStreamFilter(List<Student> students) {
        return students.stream()
                .filter(student -> student.getScore().compareTo(60.0) >= 0)
                .collect(Collectors.toList());
    }

    /**
     * 流操作返回的 list 是新建的，但里面的元素还是原来的
     * @param students
     * @return
     */
    public static List<Student> allAddTenPointScore(List<Student> students) {
        students.stream().forEach(student -> student.setScore(student.getScore() + 10));
        return students;
    }


    /**
     * 使用 Collectors.toList() 会返回新的 list
     * 原 list 不变
     * @param students
     * @return
     */
    public static List<Student> fetchFullMarksStudents(List<Student> students) {
        return students.stream().filter(student -> student.getScore() >= 100).collect(Collectors.toList());
    }

    private static class Student {
        private Double score;

        public Student() {}

        public Student(Double score) {
            this.score = score;
        }

        public void setScore(Double score) {
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
