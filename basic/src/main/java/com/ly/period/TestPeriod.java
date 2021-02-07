package com.ly.period;

import java.time.LocalDate;
import java.time.Period;

/**
 * liyang 2021-01-19
 *
 * 注意：编译器默认不适用assert检测，所以要使用时要添加参数虚拟机启动参数-ea
 */
public class TestPeriod {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2020, 4, 24);
        LocalDate endDate = LocalDate.of(2021, 1, 19);

        Period period = Period.between(startDate, endDate);
        assert(!period.isNegative());

        /**
         * 这里存在大坑，你会发现天数相差15，因为他的计算是 24 -> 19 的天数之差
         */
        System.out.println(period.getYears());  // 0
        System.out.println(period.getMonths()); // 8
        System.out.println(period.getDays());   // 15

        /**
         * 计算天数采用如下方法
         */
        long days = endDate.toEpochDay() - startDate.toEpochDay();
        System.out.println("endDate 相距 startDate 的天数：" + days);
    }
}
