package com.ly.annotation.verify.dao;

import com.ly.annotation.verify.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    public static List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("liyang", 18, "程序员"));
        personList.add(new Person("young", 18, "工程师"));

        Person person = new Person();
        person.setName("牛顿");
        person.setAge(21);

        personList.add(person);

        return personList;
    }

}
