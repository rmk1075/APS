class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int len = arr.length;
        int[] dp = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            int largestNum = -1;
            for (int j = 1; -1 < (i - j) && j <= k; j++) {
                largestNum = Math.max(largestNum, arr[i - j]);
                dp[i] = Math.max(dp[i], dp[i - j] + (largestNum * j));
            }
        }
        return dp[len];
    }
}
