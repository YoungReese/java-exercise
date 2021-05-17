package com.ly.algorithm.dp.finitestatemachine;

import java.util.Arrays;

/**
 * liyang 2021-05-17
 * 买卖股票的最佳时机，最多可以买卖两次的最大收益，第二次买入前，第一次必须已经卖出
 *
 * buy[1]  => 表示对于第 i 天的股票价格，在第 1 次持有股票时的最大收益
 * sell[2] => 表示对于第 i 天的股票价格，在第 2 次不持有股票时的最大收益
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * 原题
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例1
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 示例2
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 */
public class BestTimeToBuyAndSellStockLimit2 {

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[] buy = new int[3];
        int[] sell = new int[3];
        Arrays.fill(buy, Integer.MIN_VALUE / 2);

        for (int i = 1; i <= n; ++i) {
            // 第 i 天第 1 次交易
            buy[1] = Math.max(buy[1], 0 - prices[i - 1]);
            sell[1] = Math.max(sell[1], buy[1] + prices[i - 1]);

            // 第 i 天第 2 次交易
            buy[2] = Math.max(buy[2], sell[1] - prices[i - 1]);
            sell[2] = Math.max(sell[2], buy[2] + prices[i - 1]);
        }
        return sell[2];
    }

    public static void main(String[] args) {
        int[] prices = { 3,3,5,0,0,3,1,4 };
        int res = maxProfit(prices); // 6
        System.out.println(res);
    }
}
