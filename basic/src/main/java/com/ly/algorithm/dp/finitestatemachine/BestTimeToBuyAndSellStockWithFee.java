package com.ly.algorithm.dp.finitestatemachine;

import java.util.Arrays;

/**
 * liyang 2021-05-17
 * 买卖股票的最佳时机，无限次买卖，但是再次买入前上一次必须卖出且卖出存在交易费用
 *
 * buy[i]  => 表示第 i 天持有股票的最大收益
 * sell[i] => 表示第 i 天不持有股票的最大收益
 *
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * 原题：
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。返回获得利润的最大值。
 *
 * 示例1
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 */
public class BestTimeToBuyAndSellStockWithFee {

    public static int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;
        int[] buy = new int[n + 1];
        int[] sell = new int[n + 1];
        Arrays.fill(buy, Integer.MIN_VALUE / 2);

        for (int i = 1; i <= n; ++i) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1] - fee);
        }

        return sell[n];
    }

    public static void main(String[] args) {
        int[] prices = { 1, 3, 2, 8, 4, 9 };
        int res = maxProfitWithFee(prices, 2); // 8
        System.out.println(res);
    }
}
