class Solution {
    public int rob(int[] nums) {
        int result = 0;
        int N = nums.length;
        if(N < 4) {
            for(int num : nums) result = Math.max(num, result);
            return result;
        }
        int[] dp = new int[N];

        // 0 ~ N-1
        for(int i = 0; i < N; i++) dp[i] = nums[i];
        dp[1] = Math.max(dp[0], dp[1]);
        for(int i = 2; i < N - 1; i++) dp[i] = Math.max(dp[i - 2] + dp[i], dp[i -1]);
        result = Math.max(result, dp[N - 2]);

        // 1 ~ N
        for(int i = 0; i < N; i++) dp[i] = nums[i];
        dp[2] = Math.max(dp[1], dp[2]);
        for(int i = 3; i < N; i++) dp[i] = Math.max(dp[i - 2] + dp[i], dp[i - 1]);
        result = Math.max(result, dp[N - 1]);

        return result;
    }
}