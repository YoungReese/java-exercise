package com.ly.algorithm.dp.finitestatemachine;

import java.util.Arrays;

/**
 * liyang 2021-05-17
 * 买卖股票的最佳时机，无限次买卖，但是再次买入前上一次必须卖出且交易存在一天的冷冻期
 *
 * buy[i]  => 表示第 i 天持有股票的最大收益
 * sell[i] => 表示第 i 天不持有股票的最大收益
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 原题
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public static int maxProfitWithCooldown(int[] prices) {
        int n = prices.length;
        int[] buy = new int[n + 1];
        int[] sell = new int[n + 1];
        Arrays.fill(buy, Integer.MIN_VALUE / 2);

        for (int i = 1; i <= n; ++i) {
            int temp = i >= 2 ? sell[i - 2] : 0;
            buy[i] = Math.max(buy[i - 1], temp - prices[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
        }

        return sell[n];
    }

    public static void main(String[] args) {
        int[] prices = { 1,2,3,0,2 };
        int res = maxProfitWithCooldown(prices); // 3
        System.out.println(res);
    }
}
