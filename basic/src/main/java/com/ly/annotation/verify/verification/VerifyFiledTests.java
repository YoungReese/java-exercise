package com.ly.annotation.verify.verification;

import com.ly.annotation.verify.annotation.AnnotatedFiled;
import com.ly.annotation.verify.dao.PersonMapper;
import com.ly.annotation.verify.pojo.Person;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class VerifyFiledTests {


    public static void main(String[] args) {

        final List<Person> personList = PersonMapper.getPersonList();

        Boolean hasNull = false;

        for (Person person : personList) {
            hasNull = Arrays.stream(Person.class.getDeclaredFields())
                    .filter(field -> null != field.getAnnotation(AnnotatedFiled.class))
                    .anyMatch(field -> {
                        try {
                            Object value = PropertyUtils.getProperty(person, field.getName());
                            if (null == value) {
                                return true;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        return false;
                    });

            if (hasNull) break;
        }


        if (hasNull) System.out.println("hasNull");
        else System.out.println("not hasNull");

    }
}
