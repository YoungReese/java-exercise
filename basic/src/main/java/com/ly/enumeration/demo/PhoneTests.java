package com.ly.enumeration.demo;

import java.util.function.Function;

public class PhoneTests {

    public static void main(String[] args) {
        Integer x = 1;

        String desc = codeTrans2Desc.apply(x);

        System.out.println(desc);
    }

    private static Function<Integer, String> codeTrans2Desc = integer -> Phone.valueOf((byte) integer.intValue()).getDesc();

}
