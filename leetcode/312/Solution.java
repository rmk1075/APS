import java.util.Arrays;

class Solution {
    public int maxCoins(int[] nums) {
        int N = nums.length;
        int[] arr = new int[N + 2];
        arr[0] = arr[N + 1] = 1;
        for(int i = 1; i <= N; i++) arr[i] = nums[i - 1];
        
        // dp[left][right] = max value got from nums left~right
        int[][] dp = new int[N + 2][N + 2];
        for(int width = 1; width <= N; width++) {
            for(int l = 1; l <= N - width + 1; l++) {
                int r = l + width - 1;
                for(int i = l; i <= r; i++) dp[l][r] = Math.max(dp[l][r], (arr[l - 1] * arr[i] * arr[r + 1]) + dp[l][i - 1] + dp[i + 1][r]);
            }
        }
        return dp[1][N];
    }
}