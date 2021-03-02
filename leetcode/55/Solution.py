class Solution:
    def canJump(self, nums: List[int]) -> bool:
        dest = len(nums) - 1
        current = dest - 1
        while -1 < current:
            if dest - current <= nums[current]:
                dest = current
            current -= 1   
        return dest == 0

#  class Solution:
#     def canJump(self, nums: List[int]) -> bool:
#         n, left, right = len(nums) - 1, 0, nums[0]
#         while left < right:
#             distance = right
#             for index in range(left, right + 1):
#                 distance = max(distance, index + nums[index])
#                 if n <= distance:
#                     return True

#             left = right
#             right = distance

#         return n <= right