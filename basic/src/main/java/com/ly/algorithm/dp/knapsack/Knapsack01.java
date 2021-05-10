package com.ly.algorithm.dp.knapsack;

import java.util.ArrayList;

/**
 * liyang 2021-05-10
 *
 * 0-1 背包问题：有 N 个物品和重量为 W 的背包，每个物品的重量为 w，价值为 v，
 * 如何使得背包所装物品的价值最大 ？
 *
 * 这里限定每样物品为 1 个，所以就有装或者不装两种选择，也就是选择 0 个或者 1 个，这类问题称为 0-1 背包
 *
 * 背包容量 10
 * 现有 4 种物品，对应的 [w, v] 分别为 [2, 1]、[3, 3]、[4, 5]、[7, 9]
 *
 * 结果：12 => [3, 3] + [7, 9]
 *
 */
public class Knapsack01 { // 表示 0-1 背包
    /**
     * 经典的 0-1 背包写法
     * 虽然空间没有被压缩，但是可以找出选了哪些物品
     */
    public static int maxValue(int[] weights, int[] values, int N, int W) {

        int[][] dp = new int[N + 1][W + 1]; // 初始化值全为 0
        for (int i = 1; i <= N; ++i) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = 1; j <= W; ++j) {
                if (j >= w) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                else dp[i][j] = dp[i - 1][j];
            }
        }

        /**
         * 反推选择了哪些物品（如果存在多种选择方式，这里只选择其中一种）
         */
        ArrayList<Integer> path = new ArrayList<>();
        int C = W;
        for (int i = N; i >= 1; --i) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = C; j >= w; --j) {
                if (dp[i][j] == dp[i - 1][j]) break;
                if (dp[i][j] == dp[i - 1][j - w] + v) {
                    path.add(i - 1);
                    C -= w;
                    break;
                }
            }
        }

        /**
         * 打印结果
         */
        System.out.println("start calculated by maxValue");
        System.out.println("  背包容量是：" + W + " 可装取的最大价值是：" + dp[N][W]);
        for (int i = path.size() - 1; i >= 0; --i) {
            int index = path.get(i);
            System.out.println("    第" + (path.size() - i) + "次拿的物品是：" + (index + 1) + "号物品 => [w = " + weights[index] + ", v = " + values[index] + "]");
        }
        System.out.println("end calculated by maxValue");
        return dp[N][W];
    }

    /**
     * 空间压缩后的 0-1 背包，由于复用的 dp 数据，选择了哪些物品无法得出
     */
    public static int maxValueWithSpaceOptimized(int[] weights, int[] values, int N, int W) {

        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; ++i) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = W; j >= w; --j) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        System.out.println("start calculated by maxValueWithSpaceOptimized");
        System.out.println("  背包容量是：" + W + " 可装取的最大价值是：" + dp[W]);
        System.out.println("end calculated by maxValueWithSpaceOptimized");
        return dp[W];
    }

    /**
     * 简单测试
     */
    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 7};
        int[] values  = {1, 3, 5, 9};
        int W = 10;
        maxValue(weights, values, 4, W);
        System.out.println(" ");
        maxValueWithSpaceOptimized(weights, values, 4, W);
    }
}
