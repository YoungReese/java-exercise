package com.ly.algorithm.dp.finitestatemachine;

/**
 * liyang 2021-05-17
 * 买卖股票的最佳时机，买卖一次可以获得的最大收益
 *
 * buy  => 表示当天持有股票的最大收益
 * sell => 表示当天不持有股票的最大收益
 *
 * 参考资料：https://juejin.cn/post/6844903955030343694
 *
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 原题：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票），
 *      设计一个算法来计算你所能获取的最大利润。注意你不能在买入股票前卖出股票。
 *
 * 示例1
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 示例2
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class BestTimeToBuyAndSellStockOnce {

    public static int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE / 2, sell = 0;
        for (int i = 0, n = prices.length; i < n; ++i) {
            buy = Math.max(buy, -prices[i]);
            sell = Math.max(sell, buy + prices[i]);
        }
        return sell;
    }

    public static void main(String[] args) {
        int[] prices = { 7,1,5,3,6,4 };
        int res = maxProfit(prices); // 5
        System.out.println(res);
    }
}
