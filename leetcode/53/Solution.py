class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        dp = nums[:]
        for i in range(1, n):
            if dp[i] < dp[i - 1] + nums[i]:
                dp[i] = dp[i - 1] + nums[i]

        return max(dp)