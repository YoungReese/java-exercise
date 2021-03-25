package com.ly.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateDay {

    public static void main(String[] args) throws ParseException {

        /**
         * 测试两个时间相距几天
         */
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse("2021-03-19");
        System.out.println(date);

        System.out.println("现在与给定时间相距多少天: " + differentDays(date));


        /**
         * 测试某个时间几天前是什么日期
         */
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDay = new Date();
        System.out.println("当前时间：      " + sdf.format(currentDay));
        System.out.println("当前时间的3天前：" + sdf.format(currentDayBefore(3)));

    }


    public static int differentDays(Date date) {
        int days = (int) ((System.currentTimeMillis() - date.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static Date currentDayBefore(int days) {
        long milliseconds = System.currentTimeMillis() - 1000 * 3600 * 24 * days;
        Date date = new Date(milliseconds);
        return date;
    }

}
