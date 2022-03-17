import java.util.Arrays;

class Solution {
    static int MOD = 1_000_000_007;
    int[] dp;
    public int solution(int n, int[] money) {
        dp = new int[n + 1];
        dp[0] = 1;
        Arrays.sort(money);
        for(int m : money) {
            for(int i = m; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - m]) % MOD;
            }
        }
        return dp[n];
    }
}