package com.ly.algorithm.dp.finitestatemachine;


import java.util.Arrays;

/**
 * liyang 2021-05-17
 * 买卖股票的最佳时机，无限次买卖，但是再次买入前上一次必须卖出
 *
 * buy[i]  => 表示第 i 天持有股票的最大收益
 * sell[i] => 表示第 i 天不持有股票的最大收益
 *
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 原题
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例1
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 示例2
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 */
public class BestTimeToBuyAndSellStockWithoutTimesLimit {

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[] buy = new int[n + 1];
        int[] sell = new int[n + 1];
        Arrays.fill(buy, Integer.MIN_VALUE / 2);

        for (int i = 1; i <= n; ++i) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
        }

        return sell[n];
    }

    public static void main(String[] args) {
        int[] prices = { 7,1,5,3,6,4 };
        int res = maxProfit(prices); // 7
        System.out.println(res);
        res = maxProfitUseGreedy(prices);
        System.out.println(res);     // 7
    }

    /**
     * 本题目还可以使用贪心算法，每当后一天的股票价格高于前一天的股票价格，就可以进行一次交易
     * 不过股票交易问题，建议使用有限状态机，作为统一的解决方案
     *
     * 比如加上手续费后，贪心算法就失效了
     */
    public static int maxProfitUseGreedy(int[] prices) {
        int res = 0;
        for (int i = 1, n = prices.length; i < n; ++i) {
            if (prices[i] > prices[i - 1])
                res += prices[i] - prices[i - 1];
        }
        return res;
    }
}
