package com.ly.repeatable;

import java.lang.annotation.*;

/**
 * liyang 2021-03-27
 * 测试 java 8 新特性 => 重复注解
 *
 * 需要在自定义的注解上加上 @Repeatable(Filters.class)，然后实现 Filters 对 Filter 的存储
 */
public class RepeatingAnnotations {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {
    }

    public static void main(String[] args) {
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }

}
