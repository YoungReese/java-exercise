package com.ly.annotation.createannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * liyang 2021-01-15
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {

    /**
     * 使用一个枚举类
     */
    enum GenderType {
        Male("男"),
        Female("女");
        private final String genderStr;
        GenderType(String arg0) {
            this.genderStr = arg0;
        }
        @Override
        public String toString() {
            return genderStr;
        }
    }

    GenderType gender() default GenderType.Female;

    class User {

        @Name(value = "liyang")
        public String name;

        @Gender(gender = GenderType.Male)
        public String gender;

    }
}
