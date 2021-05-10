package com.ly.algorithm.dp.knapsack;

/**
 * liyang 2021-05-10
 *
 * 完全背包：有 N 种物品和一个可容纳重量为 W 的背包，每种物品都有无限件可用。第 i 种物品的重量是 w[i]，价值是 v[i]。
 *         求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大？
 *
 * 相对于 01 背包，某个物品的数量不是单纯的 0 或 1，而是在可选择的情况下，存在 0, 1, 2...
 *
 * dp[i][j] = max{ dp[i-1][j - k * w[i]] + k * v[i] | 0 <= k * w[i] <= j }
 *
 * 这跟 01 背包问题一样有 O(NW) 个状态需要求解，但求解每个状态的时间已经不是常数了，求解状态 dp[i][j] 的时间是 O( j / v[i])，
 * 总的复杂度可以认为是 O(N * Σ( W / c[i]))，是比较大的。
 *
 * 将完全背包问题转换为 01 背包问题，但会状态转移方程中的 dp[i - 1][j - w] 改为 dp[i][j - w]
 *
 * 01 背包状态转移方程 => dp[i][j] = max(dp[i-1][j], dp[i - 1][j - w] + v)
 * 完全背包状态转移方程 => dp[i][j] = max(dp[i-1][j], dp[i][j - w] + v)
 *
 * 将完全背包稍加改造就可以解决找零钱问题（找给定的数值，使用给定的几种货币面额，最少需要几枚硬币）
 */
public class CompleteKnapsack {
    /**
     * 完全背包，每个物品都有无穷个
     */
    public static int maxValue(int[] weights, int[] values, int N, int W) {
        int[][] dp = new int[N + 1][W + 1]; // 初始化值全为 0
        for (int i = 1; i <= N; ++i) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = 1; j <= W; ++j) {
                if (j >= w) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - w] + v);
                else dp[i][j] = dp[i - 1][j];
            }
        }
        /**
         * 打印结果
         */
        System.out.println("start calculated by maxValue");
        System.out.println("  背包容量是：" + W + " 可装取的最大价值是：" + dp[N][W]);
        System.out.println("end calculated by maxValue");
        return dp[N][W];
    }

    /**
     * 完全背包的空间压缩写法，注意 j 是正向遍历（01背包中的 j 是反向遍历）
     */
    public static int maxValueWithSpaceOptimized(int[] weights, int[] values, int N, int W) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; ++i) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = w; j <= W; ++j) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        System.out.println("start calculated by maxValueWithSpaceOptimized");
        System.out.println("  背包容量是：" + W + " 可装取的最大价值是：" + dp[W]);
        System.out.println("end calculated by maxValueWithSpaceOptimized");
        return dp[W];
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 20};
        int[] values  = {1, 6, 9, 60};
        int W = 31; // 60 + 9 * 2 + 6
        maxValue(weights, values, 4, W);
        System.out.println(" ");
        maxValueWithSpaceOptimized(weights, values, 4, W);
    }
}