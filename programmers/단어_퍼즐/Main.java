package programmers.단어_퍼즐;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] strs = {"ba","na","n","a"};
        String t = "banana";

        Solution sol = new Solution();
        int result = sol.solution(strs, t);
        System.out.println(result);
    }
}

class Solution {
    public int solution(String[] strs, String t) {
        int len = t.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < dp.length; i++) {
            for (String str : strs) {
                if (dp[i - str.length()] != Integer.MAX_VALUE && t.startsWith(str, i - str.length())) {
                    dp[i] = Math.min(dp[i], dp[i - str.length()] + 1);
                }
            }
        }

        return dp[len] == Integer.MAX_VALUE ? -1 : dp[len];
    }
}
