import java.util.Arrays;

class Solution {
    public int numSquares(int n) {
        if(n == Math.pow((int)Math.sqrt(n), 2)) return 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j * j <= i; j++) dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
        return dp[n];
    }
}