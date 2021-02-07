package com.ly.reflection;

public class User {

    private int asserts;
    public String name;
    public int age;

    public User() {
    }

    public User(int asserts, String name, int age) {
        this.asserts = asserts;
        this.name = name;
        this.age = age;
    }

    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int getAsserts() {
        return asserts;
    }

    public void setAsserts(int asserts) {
        this.asserts = asserts;
    }

}
