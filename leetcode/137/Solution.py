class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        nums_dict = {}
        for num in nums:
            if num in nums_dict.keys():
                nums_dict[num] += 1
            else:
                nums_dict[num] = 1
        
        for num in nums_dict.keys():
            if nums_dict[num] == 1:
                return num