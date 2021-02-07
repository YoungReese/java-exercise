package com.ly.serial;

import org.junit.Test;

import java.io.*;

public class TestPersonSerial {

    /**
     * 测试流程：
     * 1、Person中的serialVersionUID = 1L，执行序列化与反序列化，正常执行;
     * 2、Person增加字段email，serialVersionUID = 1L，执行反序列化，正常执行；
     * 3、serialVersionUID = 2L，执行反序列化，异常InvalidClassException。
     *
     * @throws Exception
     */
    @Test
    public void testVersion1L() throws Exception {
        File file = new File("person.out");
//
//        // 序列化
//        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
//        Person person = new Person("liyang", 18, "shenzhen");
//        oout.writeObject(person);
//        oout.close();

        // 反序列化
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject();
        oin.close();
        System.out.println(newPerson);
    }
}
