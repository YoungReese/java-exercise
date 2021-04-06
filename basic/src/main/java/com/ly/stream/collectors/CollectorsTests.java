package com.ly.stream.collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * liyang 2021-04-05
 */
public class CollectorsTests {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(2021);
        list.add(100);

        /**
         * 使用收集器 Collectors.maxBy 求出最大值
         */
        Optional<Integer> max = list.stream().collect(maxBy((x, y) -> x.compareTo(y)));
        System.out.println(max.orElse(Integer.MIN_VALUE));

        /**
         * 使用收集器 Collectors.minBy 求出最小值
         */
        Optional<Integer> min = list.stream().collect(minBy((x, y) -> x.compareTo(y)));
        System.out.println(min.orElse(Integer.MAX_VALUE));

        /**
         * 使用收集器汇总
         */
        int sum = list.stream().collect(summingInt(x -> x));
        System.out.println(sum);

        /**
         * 使用收集器
         */
        double avg = list.stream().collect(averagingInt(x -> x));
        System.out.println(avg);

        /**
         * 使用 summarizingInt 工厂方法返回的收集器
         * IntSummaryStatistics{count=4, sum=2129, min=3, average=532.250000, max=2021}
         */
        IntSummaryStatistics listStatistics = list.stream().collect(summarizingInt(x -> x));
        System.out.println(listStatistics);


        /**
         * 使用收集器的 reducing 方法 => 这方法是 Collectors.class 中的静态方法，不要和 Stream.class 中的函数混淆
         *
         * 第1个参数：起始值
         * 第2个参数：映射函数
         * 第3个参数：操作函数
         */
        int total = list.stream().collect(reducing(0, x -> x, (i, j) -> i + j));
        System.out.println(total);


        /**
         * Returns a {@code Collector} which performs a reduction of its
         * input elements under a specified {@code BinaryOperator}.  The result
         * is described as an {@code Optional<T>}.
         *
         * public static <T> Collector<T, ?, Optional<T>> reducing(BinaryOperator<T> op)
         *
         * 与上一个不同之处在于，这里返回的是 Optional<Integer>
         */
        Optional<Integer> totalSum= list.stream()
                .collect(reducing((x, y) -> x + y));
        System.out.println(totalSum.orElse(Integer.MIN_VALUE));



        /********************** 分割线 ************************/
        /**
         * 创建初始化菜单
         */
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("Fish", 300));
        menu.add(new Dish("Beef", 900));
        menu.add(new Dish("Chicken", 600));
        menu.add(new Dish("Pizza", 700));
        menu.add(new Dish("Bread", 200));


        /**
         * 使用用 groupBy 根据分组规则进行分组 （共有 3 个重载函数）
         */
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect( groupingBy(dish -> {
            if (dish.getCalories() <= 500) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        } ));
        System.out.println(dishesByCaloricLevel);


        /**
         * 分区：分组的特殊情况
         * 分区函数 partitioningBy 返回一个布尔值，这意味着得到的分组 Map 的键类型是 Boolean（ true 一组，false 一组 ）
         * 分区的好处在于保留了分区函数返回 true 或 false 的两套流元素列表。
         *
         * partitioningBy 还有个重载该函数，传入指定的收集器（ 这个不传入则是使用默认收集器toList() => return partitioningBy(predicate, toList()); ）
         */
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy((dish) -> dish.getCalories() > 600));
        System.out.println(partitionedMenu);


        /**
         * 将给定范围的数字按质数和非质数分区
         */
        Map<Boolean, List<Integer>> partitionPrimes = partitionPrimes(10);
        System.out.println(partitionPrimes);
    }

    /**
     * 实现将 [2, n] 按照质数和非质数分区
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2,n).boxed().collect(partitioningBy(isPrime));
    }
    private static Predicate<Integer> isPrime = (candidate) -> IntStream.rangeClosed(2, (int) Math.sqrt((double) candidate)).noneMatch(i -> candidate % i == 0);


    enum CaloricLevel {
        DIET("diet", "diet"), NORMAL("normal", "normal"), FAT("fat", "fat");
        private String name;
        private String desc;
        CaloricLevel(String name, String desc) {
            System.out.println("initialize a enum instance");
            this.name = name;
            this.desc = desc;
        }
        public String getName() {
            return name;
        }
        public String getDesc() {
            return desc;
        }
    }

    @Data
    @AllArgsConstructor
    static
    class Dish {
        private String name;
        private int calories;
    }
}
