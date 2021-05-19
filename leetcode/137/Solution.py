class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        one, two = 0, 0
        for num in nums:
            one, two, three = one ^ num, two | (one & num), two & num
            one, two = one & ~three, two & ~three
        return one

# class Solution:
#     def singleNumber(self, nums: List[int]) -> int:
#         nums_dict = {}
#         for num in nums:
#             if num in nums_dict.keys():
#                 nums_dict[num] += 1
#             else:
#                 nums_dict[num] = 1
        
#         for num in nums_dict.keys():
#             if nums_dict[num] == 1:
#                 return num