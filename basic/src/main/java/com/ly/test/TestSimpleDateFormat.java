package com.ly.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * liyang 2020-12-03
 * 测试SimpleDateFormat使用（注：这是一个线程不安全的类）
 *
 * 模式：
 * yyyy：年
 * MM：月
 * dd：日
 * hh：1~12小时制(1-12)
 * HH：24小时制(0-23)
 * mm：分
 * ss：秒
 * S：毫秒
 * E：星期几
 * D：一年中的第几天
 * F：一月中的第几个星期(会把这个月总共过的天数除以7)
 * w：一年中的第几个星期
 * W：一月中的第几星期(会根据实际情况来算)
 * a：上下午标识
 * k：和HH差不多，表示一天24小时制(1-24)。
 * K：和hh差不多，表示一天12小时制(0-11)。
 * z：表示时区
 *
 * 结果：
 * 2020-12-03 10:54:51 星期四 49 上午
 *
 * Process finished with exit code 0
 *
 */
public class TestSimpleDateFormat {

    public static void main(String[] args){
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss E w a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));
    }

}
