package com.ly.duration;

import java.time.Duration;
import java.time.Instant;

/**
 * liyang 2021-01-19
 *
 * Duration类表示秒或纳秒时间间隔，适合处理较短的时间，需要更高的精确性。我们能使用between()方法比较两个瞬间的差
 *
 * 结果：
 * 60
 * 1
 * 60000
 * 60000000000
 *
 * Process finished with exit code 0
 */
public class TestDuration {
    public static void main(String[] args) {
        Instant start = Instant.parse("2017-10-03T10:15:30.00Z");
        Instant end = Instant.parse("2017-10-03T10:16:30.00Z");

        Duration duration = Duration.between(start, end);
        System.out.println(duration.getSeconds()); // 60
        System.out.println(duration.toMinutes());  // 1
        System.out.println(duration.toMillis());   // 60 * 1000
        System.out.println(duration.toNanos());    // 60 * 1000 * 1000 * 1000
    }
}
