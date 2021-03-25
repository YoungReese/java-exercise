package com.ly.annotation.verify.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotatedFiled {
    String desc() default "";

    boolean valueMap() default false;

    //    FieldValueMap[] valueDescMap() default {};
}
