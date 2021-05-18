package com.ly.algorithm.dp.editstring;

/**
 * liyang 2021-05-18
 * 正则表达式匹配
 *
 * 参考资料：https://palette25.github.io/2018/09/05/LeetCode-Regular-Expression-Matching/
 * 注：参考答案的代码（solution1）有些测试案例过不了，比如 s = "aa", p = "ab*a*c*a"; 应该输出 true，代码输出 false
 * 原因在于，即使我当前位置可以被匹配且 p[j - 1] == '*'，那么我也尝试使用 0 次试试
 * 只需要稍作修改即可，具体见如下代码
 */
public class RegularExpressionMatching {

    // solution 1
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        if (n == 0) return m == 0;
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        dp[0][0] = true;
        for (int j = 2; j <= n; ++j) {
            if (cp[j - 1] == '*') dp[0][j] = dp[0][j - 2]; // 使用 0 次的初始化操作
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (cp[j - 1] != '*') {
                    if (cp[j - 1] == cs[i - 1] || cp[j - 1] == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else { // cp[j - 1] == '*'
                    if (cp[j - 2] == cs[i - 1] || cp[j - 2] == '.') { // i 位置可以匹配
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];      // 使用 n（1 包括在 n 中） 次或者 0 次，利用前向推导累计
                    } else {
                        dp[i][j] = dp[i][j - 2];                      // 使用 0 次
                    }
                }
            }
        }
        return dp[m][n];
    }

    // solution 2
    public static boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        if (n == 0) return m == 0;
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        dp[0][0] = true;
        for (int j = 2; j <= n; ++j) {
            if (cp[j - 1] == '*') dp[0][j] = dp[0][j - 2]; // 使用 0 次的初始化操作
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (cp[j - 1] != '*') {
                    if (cp[j - 1] == '.' || cp[j - 1] == cs[i - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else { // cp[j - 1] == '*'
                    if (cp[j - 2] != cs[i - 1] && cp[j - 2] != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        /**
                         * dp[i - 1][j] => 表示 j 除了匹配 i 位置，还将尝试匹配 i - 1 位置，即 ？* 使用 n 次
                         * dp[i][j - 1] => 表示 j - 1 位置匹配 i 位置，位置 j 不使用，即 ？* 使用 1 次
                         * dp[i][j - 1] => 表示 j - 2 位置匹配 i 位置，位置 j 和 j - 1 不使用，即 ？* 使用 0 次
                         */
                        //               n 次             1 次             0 次
                        // dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2]; // 1 次可以被包含在 n 次中
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "aa", p = "ab*a*c*a";
        boolean res = isMatch(s, p);
        System.out.println(res);
        res = isMatch2(s, p);
        System.out.println(res);
    }
}
