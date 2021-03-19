class Solution(object):
    def sortColors(self, nums):
        numsList = [0, 0, 0]
        for num in nums:
            numsList[num] += 1
        
        temp = [0] * numsList[0] + [1] * numsList[1] + [2] * numsList[2]
        for i in range(len(temp)):
            nums[i] = temp[i]