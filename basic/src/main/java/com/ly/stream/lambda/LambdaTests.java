package com.ly.stream.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * liyang 2021-03-17
 *
 * 词法作用域（Lexical scoping）
 * Lambda的词法作用域与外部环境具有相通的语义，而内部类与外部环境没有想通的语义
 *
 * 基于词法作用域的理念，lambda 表达式不可以掩盖任何其所在上下文中的局部变量，
 * 它的行为和那些拥有参数的控制流结构（例如 for 循环和 catch 从句）一致。举个例子说明：
 * int i = 0;
 * int sum = 0;
 * for (int i = 1; i < 10; i += 1) { // 这里会出现编译错误，因为i已经在for循环外部声明过了
 *   sum += i;
 * }
 *
 * 参考资料：
 * http://lucida.me/blog/java-8-lambdas-insideout-language-features/
 */
public class LambdaTests {

    Runnable r1 = () -> { System.out.println(this); };
    Runnable r2 = () -> { System.out.println(toString()); };
    Runnable r3 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };
    Runnable r4 = new Runnable() {
        @Override
        public void run() {
            System.out.println(toString());
        }
    };

    public String toString() {  return "Hello, world"; }

    public static void main(String... args) {
        new LambdaTests().r1.run(); // Hello, world
        new LambdaTests().r2.run(); // Hello, world
        new LambdaTests().r3.run(); // com.ly.stream.lambda.LambdaTests$1@34a245ab
        new LambdaTests().r4.run(); // com.ly.stream.lambda.LambdaTests$2@7cc355be

        /**
         * lambda 表达式对值封闭，对变量开放的原文是：lambda expressions close over values, not variables
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        /**
         * Illegal, close over values
         */
//        int sum = 0;
//        list.forEach(e -> { sum += 1); }); // Variable used in lambda expression should be final or effectively final

        /**
         * Legal, open over variables
         */
        List<Integer> copyList = new ArrayList<>();
        list.forEach(e -> { copyList.add(e); });
        copyList.stream().forEach(System.out::println);


        /**
         * lambda 表达式不支持修改捕获变量的另一个原因是我们可以使用更好的方式来实现同样的效果：使用规约（reduction）。
         * java.util.stream 包提供了各种通用的和专用的规约操作（例如 sum、min 和 max），
         * 就上面的例子而言，我们可以使用规约操作（在串行和并行下都是安全的）来代替 forEach：
         *
         * 如果操作符具有可结合性（associative），那么规约操作就可以容易的被并行化。
         */
        int sum01 = list.stream().mapToInt(e -> e + 1).sum();                               // 专用规约
        int sum02 = list.stream().mapToInt(e -> e + 1).reduce(0 , (x, y) -> x + y);  // 通用规约
        System.out.println(sum01);  // 14
        System.out.println(sum02);  // 14


    }

}