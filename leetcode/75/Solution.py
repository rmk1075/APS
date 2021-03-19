class Solution(object):
    def sortColors(self, nums):
        current, left, right = 0, 0, len(nums) - 1
        while current <= right:
            if nums[current] == 0:
                nums[current], nums[left] = nums[left], nums[current]
                left += 1
                current += 1
            elif nums[current] == 2:
                nums[current], nums[right] = nums[right], nums[current]
                right -= 1
            else:
                current += 1

# class Solution(object):
#     def sortColors(self, nums):
#         numsList = [0, 0, 0]
#         for num in nums:
#             numsList[num] += 1
        
#         temp = [0] * numsList[0] + [1] * numsList[1] + [2] * numsList[2]
#         for i in range(len(temp)):
#             nums[i] = temp[i]