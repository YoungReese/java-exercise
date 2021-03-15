package com.ly.stream.sorted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * liyang 2021-03-15
 * 过滤分数大于等于60分的同学
 */
public class SortedTests {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(90.0));
        students.add(new Student(50.0));
        students.add(new Student(60.0));
        students.add(new Student(100.0));
        System.out.println(sortedByStreamSorted(students));
        System.out.println(sortedByStreamSortedReversed(students));
    }

    /**
     * 从小到大升序
     * @param students
     * @return
     */
    private static List<Student> sortedByStreamSorted(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getScore))
                .collect(Collectors.toList());
    }

    /**
     * 从大到小降序
     * @param students
     * @return
     */
    private static List<Student> sortedByStreamSortedReversed(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getScore).reversed())
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
