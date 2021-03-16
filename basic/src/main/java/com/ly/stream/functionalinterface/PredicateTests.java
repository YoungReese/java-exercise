package com.ly.stream.functionalinterface;

import java.util.function.Predicate;

/**
 * liyang 2021-03-16
 * 测试断言型函数式接口 Predicate => 唯一抽象函数： boolean test(T t);
 *                            => 接受一个输入参数，返回布尔型结果
 */
public class PredicateTests {
    public static void main(String[] args) {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {                               // 大于等于18返回true
                return integer >= 18;
            }
        };

        Predicate<Integer> predicateLambda = integer -> integer >= 18;           // 大于等于18返回true

        /**
         * 测试 test
         */
        System.out.println(predicate.test(17) ? "成年" : "未成年");
        System.out.println(predicateLambda.test(18) ? "成年" : "未成年");


        /**
         * 测试 and
         * default Predicate<T> and(Predicate<? super T> other) {}
         * 数字是否在区间 [18, 20]
         */
        boolean and = predicateLambda.and(integer -> integer <= 20).test(19); // 传入的是19，在区间[18, 20]中，返回true
        System.out.println(and);

        /**
         * 测试 or
         * default Predicate<T> or(Predicate<? super T> other) {}
         * 数字是否 <= 5 或者 >= 18  =>  (-inf, 5) or [18, inf)
         */
        boolean or = predicateLambda.or(integer -> integer <= 5).test(5);     // 传入的是5，返回true
        System.out.println(or);

        /**
         *
         * 测试 negate
         * default Predicate<T> negate() {}
         * 调用 !test(t)
         */
        boolean negate = predicateLambda.negate().test(18);                   // 传入的是18，返回false
        System.out.println(negate);

        /**
         * 测试 isEqual
         * static <T> Predicate<T> isEqual(Object targetRef) {}
         *
         * isEqual 类似于equals() 区别在于：
         * 先判断对象是否为 null，不为 null 的话再使用 equals() 方法进行比较，
         * 比较的内容是 test() 中传入的参数。
         */
        String string = "test predicate";
        boolean isEqual = Predicate.isEqual(string).test("test predicate");   // 返回true
        System.out.println(isEqual);

    }
}
