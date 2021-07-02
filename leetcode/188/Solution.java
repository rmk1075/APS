class Solution {
    public int maxProfit(int k, int[] prices) {
        int N = prices.length;
        if(k == 0 || N < 2) return 0;

        int[][] dp = new int[k + 1][N];
        for(int i = 1; i < k + 1; i++) {
            int val = -prices[0];
            for(int j = 1; j < N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + val);
                val = Math.max(val, dp[i - 1][j - 1] - prices[j]);
            }
        }

        return dp[k][N - 1];
    }
}