class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        i = len(nums) - 1
        while 0 < i and nums[i - 1] >= nums[i]:
            i -= 1
        
        if i == 0:
            nums.reverse()
            return
        
        i -= 1
        j = len(nums) - 1
        while nums[j] <= nums[i]:
            j -= 1

        nums[j], nums[i] = nums[i], nums[j]

        left, right = i + 1, len(nums) - 1
        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left += 1
            right -= 1