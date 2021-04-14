package com.ly.stream.chap06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

/**
 * liyang 2021-04-14
 * My simple implementation class for {@code Collector}.
 *
 * Collector 接口包含了一系列方法，为实现具体的规约操作(即收集器)提供了范本！！！
 *
 * 我们已经看过了 Collector 接口中实现的许多收集器，例如 toList 或 groupingBy。
 * 这也意味着，你可以为 Collector 接口提供自己的实现，从而自由地创建自定义规约操作。
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 创建集合操作的起始点
     *
     * Java API 所提供的收集器在需要返回空列表时使用了 Collections.emptyList() 这个单例（singleton）。
     * 源码分析：
     * public static <T> Collector<T, ?, List<T>> toList() {
     *     return new CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
     *                                (left, right) -> { left.addAll(right); return left; },
     *                                 CH_ID);
     * }
     *
     * // Constructs an empty list with an initial capacity of ten. （10）
     * public ArrayList() {
     *     this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
     * }
     *
     * // Shared empty array instance used for default sized empty instances. We
     * // distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * // first element is added.
     * // => 初始大小 10，每次 grow 默认增加一半
     *
     * private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {}; // 注意：没有使用 new
     */
    @Override
    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<T>();
    }

    /**
     * 累计遍历过的项目，原位修改累加器
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> list.add(item);
    }

    /**
     * 恒等函数，因为本收集器实现类的 accumulator 就是中间收集结果，
     * 不需要做转换，也可以使用 Function.identity();
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return i -> i;
    }

    /**
     * 修改第一个累加器，将其与第二个累加器内容合并，返回修改后的第一个累加器
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 为收集器添加 IDENTITY_FINISH 和 CONCURRENT 标志
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
