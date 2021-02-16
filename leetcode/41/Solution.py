import sys

class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        nums.sort()

        i = 0
        while i < len(nums) and nums[i] <= 0:
            i += 1
        
        if i == len(nums):
            return 1

        num = 1
        while i < len(nums) and num < sys.maxsize:
            if num != nums[i]:
                return num
            
            while i < len(nums) and num == nums[i]:
                i += 1
            
            num += 1
        
        if i == len(nums):
            return num