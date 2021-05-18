class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        num_dict = {}
        for num in nums:
            if num in num_dict.keys():
                num_dict[num] = 2
            else:
                num_dict[num] = 1
        
        for num in num_dict.keys():
            if num_dict[num] == 1:
                return num