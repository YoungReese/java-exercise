package com.ly.serial;

import java.io.*;

/**
 * liyang 2021-01-19
 * Junit Test in package test
 *
 * 实现Serializable（用于java对象的序列化和反序列化）后需要给一个serialVersionUID，避免不同jdk自生成的差异；
 * 发版的时候，serialVersionUID增加，放弃之前的版本，之前版本反序列化报InvalidClassException错误；
 * 发版的时候，serialVersionUID不变，兼容之前的版本，之前版本序列化正常；
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
    private String address;
    private String email;

    public Person() {
    }

    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Person(String name, Integer age, String address,String email) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
