package com.ly.stream.chap11.v2;

import static com.ly.stream.chap11.DelayUtils.delay;
import static com.ly.stream.chap11.v2.Util.format;

/**
 * liyang 2021-04-13
 * 远程 Discount 服务
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }

}
