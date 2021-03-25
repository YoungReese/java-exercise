package com.ly.annotation.verify.pojo;

import com.ly.annotation.verify.annotation.AnnotatedFiled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @AnnotatedFiled(desc = "姓名")
    String name;
    @AnnotatedFiled(desc = "年龄")
    Integer age;

    String job;
}
