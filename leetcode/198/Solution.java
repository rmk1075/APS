class Solution {
    public int rob(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];

        dp[0] = nums[0];
        if(1 < N) dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < N; i++) dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        
        return dp[N - 1];
    }
}