package com.ly.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateDay {

    public static void main(String[] args) throws ParseException {

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat1.parse("2021-03-19");
        System.out.println(date);

        System.out.println("现在与给定时间相距多少天: " + differentDays(date));
    }


    public static int differentDays(Date date) {
        int days = (int) ((System.currentTimeMillis() - date.getTime()) / (1000 * 3600 * 24));
        return days;
    }

}
