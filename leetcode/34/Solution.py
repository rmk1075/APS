class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        left, right = 0, len(nums) - 1
        mid = self.binary(left, right, nums, target)

        if mid == -1:
            return [-1, -1]

        left, right = mid, mid
        while 0 < left and nums[left] == target:
            left -= 1
        if nums[left] is not target:
            left += 1

        while right < len(nums) - 1 and nums[right] == target:
            right += 1
        if nums[right] is not target:
            right -= 1
            
        return [left, right]
    
    def binary(self, left: int, right: int, nums: List[int], target: int) -> int:
        while left < right:
            mid = int((left + right) / 2)
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid
        
        if left == right and nums[left] == target:
            return left

        return -1