package com.ly.stream.chap12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

/**
 * liyang 2021-04-13
 */
public class DateTimeExamples {

    public static void main(String[] args) {
        localDateExamples();
        System.out.println("==============");
        localTimeExamples();
        System.out.println("==============");
        localDateTimeExamples();
        System.out.println("==============");
        instantExamples();
        System.out.println("==============");
        durationExamples();
        System.out.println("==============");
        periodExamples();
        System.out.println("==============");
        withAttributeExamples();
        System.out.println("==============");
        localDateUpdateAttr();
        System.out.println("==============");
        temporalAdjusterExamples();
        System.out.println("==============");
        dateTimeFormatExamples();
        System.out.println("==============");
        dateTimeFormatterBuilderExamples();
        System.out.println("==============");
        zoneIdExamples();
        System.out.println("==============");
        zoneOffsetExamples();
    }

    private static void zoneOffsetExamples() {
        ZoneOffset newYorkOffsetLondon = ZoneOffset.of("-05:00");

        LocalDateTime dateTimeInLondon = LocalDateTime.of(2021, 04, 13, 20, 45);
        System.out.println("dateTimeInLondon:  " + dateTimeInLondon);

        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTimeInLondon, newYorkOffsetLondon);
        System.out.println("dateTimeInNewYork: " + dateTimeInNewYork);
    }

    private static void zoneIdExamples() {
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        System.out.println("zone: " + romeZone);

        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println("TimeZone-default: " + zoneId);

        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
        LocalDate date = LocalDate.of(2021, 04, 13);
        ZonedDateTime zdt1 = date.atStartOfDay(shanghaiZone);
        System.out.println("zdt1: " + zdt1);

        LocalDateTime dateTime = LocalDateTime.of(2021, 04, 13, 19, 59, 21);
        ZonedDateTime zdt2 = dateTime.atZone(shanghaiZone);
        System.out.println("zdt2: " + zdt2);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(shanghaiZone);
        System.out.println("zdt3: " + zdt3);

        /**
         * 通过 ZoneOffset，你还可以将 LocalDateTime 转换为 Instant:
         */
        LocalDateTime dateTime2 = LocalDateTime.of(2021, Month.APRIL, 13, 12, 21);
        // 2021-04-13T12:21
        System.out.println(dateTime2);
        Instant instantFromDateTime = dateTime2.toInstant(ZoneOffset.UTC); // 世界时
        // 2021-04-13T12:21:00Z
        System.out.println(instantFromDateTime);
        // 2021-04-13T20:21+08:00[Asia/Shanghai]
        System.out.println(instantFromDateTime.atZone(shanghaiZone));      // 东8时： + 8小时

    }

    private static void dateTimeFormatterBuilderExamples() {
        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        LocalDate now = LocalDate.now();
        String s1 = now.format(italianFormatter);
        System.out.println("s1: " + s1);
    }

    private static void dateTimeFormatExamples() {
        LocalDate date1 = LocalDate.of(2021, 04, 13);
        // 20210413
        String s1 = date1.format(DateTimeFormatter.BASIC_ISO_DATE);
        // 2021-04-13
        String s2 = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);

        /**
         * 和老的 java.util.DateFormat 相比较，所有的 DateTimeFormatter 实例都是线程安全的
         */
        LocalDate date2 = LocalDate.parse("20210413", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date3 = LocalDate.parse("2021-04-13", DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("date2: " + date2);
        System.out.println("date3: " + date3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date1.format(formatter);
        System.out.println("formattedDate: " + formattedDate);

        LocalDate date4 = LocalDate.parse(formattedDate, formatter);
        System.out.println("date4: " + date4);

        /**
         * 创建一个本地化的 DateTimeFormatter
         */
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date5 = LocalDate.of(2021, 04, 13);
        // 13. aprile 2021
        String formattedDate2 = date5.format(italianFormatter);
        System.out.println("formattedDate2: " + formattedDate2);

        LocalDate date6 = LocalDate.parse(formattedDate2, italianFormatter);
        System.out.println("date6: " + date6);
    }

    private static void temporalAdjusterExamples() {
        // 2021-04-13
        LocalDate date1 = LocalDate.of(2021, 04, 13);
        // 2021-04-19
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        // 2021-04-30
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());

        System.out.println("date2: " + date2);
        System.out.println("date3: " + date3);
    }

    private static void localDateUpdateAttr() {
        // 2021-04-13
        LocalDate date1 = LocalDate.of(2021, 04, 13);
        // 2021-04-20
        LocalDate date2 = date1.plusWeeks(1);
        // 2011-04-20
        LocalDate date3 = date2.minusYears(10);
        // 2011-10-20
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);

        System.out.println("date1: " + date1);
        System.out.println("date2: " + date2);
        System.out.println("date3: " + date3);
        System.out.println("date4: " + date4);
    }

    private static void withAttributeExamples() {
        // 2021-04-13
        LocalDate date1 = LocalDate.of(2021, 04, 13);
        // 2022-04-13
        LocalDate date2 = date1.withYear(2022);
        // 2022-04-25
        LocalDate date3 = date2.withDayOfMonth(25);
        // 2022-09-25
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("date1: " + date1);
        System.out.println("date2: " + date2);
        System.out.println("date3: " + date3);
        System.out.println("date4: " + date4);
    }

    private static void periodExamples() {
        Period period = Period.between(LocalDate.of(2021, 04, 13), LocalDate.of(2021, 04, 3));
        // P-10D 相差10天
        System.out.println("Period between: " + period);

        /**
         * 对比 Duration 和 Period
         */
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration fourMinutes = Duration.of(4, ChronoUnit.MINUTES);

        Period tenDay = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(twoYearsSixMonthsOneDay); // P2Y6M1D
    }

    private static void durationExamples() {
        /**
         * 由于 Duration 类主要用于以秒和纳秒衡量时间的长短，你不能仅向 between 方法传递一个 LocalDate 对象做参数。
         * 如果你需要以年、月或者日的方式对多个时间单位建模，可以使用 Period 类。使用该类的工厂方法 between，你可以使用得到两个 LocalDate 之间的时长。
         */
        LocalTime time1 = LocalTime.of(21, 50, 10);
        LocalTime time2 = LocalTime.of(22, 50, 10);
        LocalDateTime dateTime1 = LocalDateTime.of(2021, 11, 17, 21, 50, 10);
        LocalDateTime dateTime2 = LocalDateTime.of(2021, 11, 17, 23, 50, 10);
        Instant instant1 = Instant.ofEpochSecond(1000 * 60 * 2);
        Instant instant2 = Instant.ofEpochSecond(1000 * 60 * 3);

        Duration d1 = Duration.between(time1, time2);
        Duration d2 = Duration.between(dateTime1, dateTime2);
        Duration d3 = Duration.between(instant1, instant2);
        // PT1H 相差1小时
        System.out.println("d1: " + d1);
        // PT2H 相差2小时
        System.out.println("d2: " + d2);
        // PT16H40M 相差16小时40分钟
        System.out.println("d3: " + d3);
    }

    private static void instantExamples() {
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        // 2 秒之后再加上100万纳秒（1秒）
        Instant.ofEpochSecond(2, 1_000_000_000);
        // 4 秒之前的100万纳秒（1秒）
        Instant.ofEpochSecond(4, -1_000_000_000);

        /**
         * instant 无法直接使用，因为它的实际初衷是为了便于机器使用
         * 但可以通过 Duration 和 Period 类使用 Instant
         */
        // Exception in thread "main" java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth
        // int day = Instant.now().get(ChronoField.DAY_OF_MONTH);
    }

    private static void localDateTimeExamples() {
        // 2021-04-13T17:45:59
        LocalTime time = LocalTime.of(17, 45, 59);
        LocalDate date = LocalDate.of(2021, 04, 13);

        LocalDateTime dt1 = LocalDateTime.of(2021, Month.APRIL, 13, 17, 45, 59);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        System.out.println(dt2);
        LocalDateTime dt3 = date.atTime(17, 45, 59);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);
    }

    private static void localTimeExamples() {
        LocalTime localTime = LocalTime.of(17, 45, 21);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();

        System.out.println(String.format("hour: %s\nminute: %s\nsecond: %s", hour, minute, second));

        LocalTime time = LocalTime.parse("21:29:59");
        System.out.println(time);
    }

    private static void localDateExamples() {
        LocalDate localDate = LocalDate.of(2021, 4, 13);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int day = localDate.getDayOfMonth();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int len = localDate.lengthOfMonth();
        boolean leap = localDate.isLeapYear();

        System.out.println(String.format("year: %s\nmonth: %s\nday: %s\nday of week: %s\nlen: %s\nleap: %s", year, month, day, dayOfWeek, len, leap));

        LocalDate today = LocalDate.now();
        System.out.println(today);

        localDate.get(ChronoField.YEAR);
        localDate.get(ChronoField.MONTH_OF_YEAR);
        localDate.get(ChronoField.DAY_OF_MONTH);

        LocalDate date = LocalDate.parse("2021-04-13");
        System.out.println(date);
    }

}
