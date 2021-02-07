package com.ly.date;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {


    /**
     * @Description: 任意时间字符串转换成时间，无需指定解析模板
     * @param:  日期
     * @return:
     * @Author: Neo
     * @Date: 2018/11/9 9:50
     * @Version: 1.0
     */
    public static Date parseStringToDate(String date) throws ParseException {
        Date result;
        String parse = date.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        DateFormat format = new SimpleDateFormat(parse);
        result = format.parse(date);
        return result;
    }


    public static void main(String[] args) {
//        try {
//            Date d1 = parseStringToDate("2018年11月1日");
//            Date d2 = parseStringToDate("2018-11-2");
//            Date d3 = parseStringToDate("2018/11/3");
//            Date d4 = parseStringToDate("2018.11.4");
//            Date d5 = parseStringToDate("2018115");
//            Date d6 = parseStringToDate("2018年11月1日 11:11:11");
//            Date d7 = parseStringToDate("2018115 12:12:12");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println("d1："+sdf.format(d1));
//            System.out.println("d2："+sdf.format(d2));
//            System.out.println("d3："+sdf.format(d3));
//            System.out.println("d4："+sdf.format(d4));
//            System.out.println("d5："+sdf.format(d5));
//            System.out.println("d6："+sdf.format(d6));
//            System.out.println("d7："+sdf.format(d7));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        testNow();
    }


    public static void testNow() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());

        Date d1 = null;
        try {
            d1 = parseStringToDate("2018年11月1日");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(d1);
    }

}
