package com.ly.stream.test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

    /**
     * 3 -> 6
     * @param args
     */
    public static void main(String[] args) {
        String oldAlarmUsers = "1,2,3,4,5";

        String oldAlarmUser = "3";
        String newAlarmUser = "5";

        String alarmUsers = Stream.of(oldAlarmUsers
                .split(","))
                .filter(s -> !s.equals(oldAlarmUser))
                .filter(s -> !s.equals(newAlarmUser))
                .collect(Collectors.joining(","));

        System.out.println(alarmUsers + "," + newAlarmUser);
    }
}
