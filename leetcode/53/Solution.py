class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        maxVal, val = nums[0], nums[0]
        for num in nums[1:]:
            if num < val + num:
                val += num
            else:
                val = num
                
            if maxVal < val:
                maxVal = val
                
        return maxVal

# class Solution:
#     def maxSubArray(self, nums: List[int]) -> int:
#         n = len(nums)
#         dp = nums[:]
#         for i in range(1, n):
#             if dp[i] < dp[i - 1] + nums[i]:
#                 dp[i] = dp[i - 1] + nums[i]

#         return max(dp)