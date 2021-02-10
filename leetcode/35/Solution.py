class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        left, right= 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
        
        return left

# class Solution:
#     def searchInsert(self, nums: List[int], target: int) -> int:
#         left, right= 0, len(nums) - 1
#         mid = int((left + right) / 2)
#         while left < right:
#             mid = int((left + right) / 2)
#             if nums[mid] == target:
#                 return mid
#             elif nums[mid] < target:
#                 left = mid + 1
#             else:
#                 right = mid
        
#         if nums[left] < target:
#             return left + 1
#         else:
#             return left