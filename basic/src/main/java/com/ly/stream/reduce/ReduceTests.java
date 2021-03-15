package com.ly.stream.reduce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * liyang 2021-03-15
 * reduce是将传入一组值，根据计算模型输出一个值。例如求一组值的最大值、最小值、和等等。
 */
public class ReduceTests {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            numbers.add(i);
        }
        System.out.println(calcTotal(numbers));                        // 5050
        System.out.println(calcTotalWithInitial(numbers));             // 0

        List<Student> students = new ArrayList<>();
        students.add(new Student(59.0));
        students.add(new Student(61.0));
        students.add(new Student(80.0));
        System.out.println(calcTotalScoresInStudent(students));        // 200.0
        System.out.println(calcTotalScoreByStreamMapReduce(students)); // 200.0

    }

    /**
     * Optional<T> reduce(BinaryOperator<T> accumulator);
     * 使用没有初始值对集合中的元素进行累加
     * @param numbers 集合元素
     * @return 累加结果
     *
     * 注意：入参T，出参也是T
     */
    private static Integer calcTotal(List<Integer> numbers) {
        return numbers.stream()
                .reduce((total, number) -> total + number).get();
    }

    /**
     * T reduce(T identity, BinaryOperator<T> accumulator);
     * 使用有初始值对集合中的元素进行累加
     * @param numbers 集合元素
     * @return 累加结果
     *
     * 注意：入参T，出参也是T
     */
    private static Integer calcTotalWithInitial(List<Integer> numbers) {
        return numbers.stream()
                .reduce(-5050, (total, number) -> total + number);
    }

    /**
     * U reduce(U identity,
     *          BiFunction<U, ? super T, U> accumulator,
     *          BinaryOperator<U> combiner);
     * 使用有初始值对集合中的对象进行累加
     *
     * 注意：入参T，出参是U
     * 这里第1个和第2个参数在本测试中已经唯一确定了，第3个参数在串行stream流中不起作用，但在并行流中就起作用了
     */
    private static Double calcTotalScoresInStudent(List<Student> students) {
        return students.stream()
                .reduce(Double.valueOf(0.0),
                        (total, student) -> total + student.getScore(),
                        (double1, double2) -> { // 对应函数在串行流无作用
                            System.out.println(double1 + double2);
                            return double1 + double2;
                        });
    }

    /**
     * 计算学生总成绩的替代方法
     * 先使用map将学生集合转换为分数的集合
     * 再使用reduce调用第一个重载方法计算总和
     * @param students 学生集合
     * @return 分数总和
     */
    private static Double calcTotalScoreByStreamMapReduce(List<Student> students) {
        return students.stream()
                .map(Student::getScore)
                .reduce((total, score) -> total + score).get();
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
