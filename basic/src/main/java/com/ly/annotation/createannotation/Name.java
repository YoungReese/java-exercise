package com.ly.annotation.createannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * liyang 2021-01-15
 * 使用元注解来自定义一个注解
 *
 * Target注解的作用是：描述注解的使用范围(即被修饰的注解可以用在什么地方).
 * Retention注解的作用是：描述注解保留的时间范围(即：被描述的注解在它所修饰的类中可以被保留到何时).
 * Documented注解的作用是：描述在使用 javadoc 工具为类生成帮助文档时是否要保留其注解信息。
 * Inherited注解的作用是：使被它修饰的注解具有继承性（如果某个类使用了被@Inherited修饰的注解，则其子类将自动具有该注解）。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    /**
     * name
     */
    String value() default "none";
}
