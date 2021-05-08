class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        max_cnt = 0
        num_set = set(nums)
        for num in num_set:
            if num - 1 in num_set:
                continue
            cnt = 1
            while num + 1 in num_set:
                num += 1
                cnt += 1
            
            max_cnt = max(max_cnt, cnt)
        return max_cnt
