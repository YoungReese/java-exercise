package com.ly.stream.functionalinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * liyang 2021-03-16
 * 测试函数式接口 Consumer => 唯一抽象函数：void accept(T t);
 *                      => 接受一个输入参数，无返回结果
 */
public class ConsumerTests {
    public static void main(String[] args) {

        final Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("execute => consumer: consumed " + integer);
            }
        };

        final Consumer<Integer> consumerLambda = integer -> {
            System.out.println("execute => consumerLambda: consumed " + integer);
        };


        /**
         * execute => consumer: consumed 2021
         * execute => consumerLambda: consumed 2021
         */
        consumer.accept(2021);
        consumerLambda.accept(2021);

        /**
         * execute => consumerLambda: consumed 2022
         * execute => consumer: consumed 2022
         */
        consumerLambda.andThen(consumer).accept(2022);
        System.out.println(" ");



        /**
         * 实际应用测试
         * 参考资料：https://www.jianshu.com/p/0b955173045e
         */
        List<Person> lisiList = new ArrayList<>();
        Consumer <Person> consumer2  =  x -> {
            if (x.name.equals("lisi")){
                lisiList.add(x);
            }
            System.out.println("lisiList.add(x);");
        };

        /**
         * 循环遍历每一个对象，先调用 accept 、然后调用 after 进行处理
         * 将给定的一批用户里面的名称为"lisi"且年龄大于22岁的用户都给打包起来
         */
        consumer2 = consumer2.andThen(
                x -> {
                    lisiList.removeIf(y -> y.age < 23);
                    System.out.println("lisiList.removeIf(y -> y.age < 23);");
                }
        );

        /**
         * 传入可变参数后转换成 stream 流：
         * public static<T> Stream<T> of(T... values) {
         *     return Arrays.stream(values);
         * }
         *
         * 然后使用 forEach：
         * Performs an action for each element of this stream.
         *
         */
        Stream.of(
                new Person(21,"zhangsan"),
                new Person(22,"lisi"),
                new Person(23,"wangwu"),
                new Person(24,"wangwu"),
                new Person(23,"lisi"),
                new Person(26,"lisi"),
                new Person(26,"zhangsan")
        ).forEach(consumer2);
        System.out.println("after filter: ");
        System.out.println(lisiList);
    }

    static class Person {
        String name;
        int age;

        public Person(int age, String name) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
