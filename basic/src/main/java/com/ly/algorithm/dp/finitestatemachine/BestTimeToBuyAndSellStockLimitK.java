package com.ly.algorithm.dp.finitestatemachine;

import java.util.Arrays;

/**
 * liyang 2021-05-17
 * 买卖股票的最佳时机，最多可以买卖 k 次的最大收益，第二次买入前，第一次必须已经卖出
 *
 * buy[j]  => 表示对于第 i 天的股票价格，在第 j 次持有股票时的最大收益
 * sell[j] => 表示对于第 i 天的股票价格，在第 j 次不持有股票时的最大收益
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * 原题
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例1
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例2
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *  
 */
public class BestTimeToBuyAndSellStockLimitK {

    public static int maxProfit(int[] prices, int k) {
        int n = prices.length;
        if ((n >> 1) <= k) return maxProfitUseGreedy(prices);

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE / 2);
        Arrays.fill(sell, 0);
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }

    public static int maxProfitUseGreedy(int[] prices) {
        int res = 0;
        for (int i = 1, n = prices.length; i < n; ++i) {
            if (prices[i] > prices[i - 1])
                res += prices[i] - prices[i - 1];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] prices = { 3,2,6,5,0,3 };
        int res = maxProfit(prices, 2);
        System.out.println(res); // 7
    }
}
