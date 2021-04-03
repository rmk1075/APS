class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        result = []
        nums = sorted(nums)
        self.dfs(result, nums, [], len(nums), 0)
        return result
    
    def dfs(self, result: List[int], nums: List[int], subsets: List[int], N: int, index: int):
        result.append(subsets[:])
        if index == N:
            return
        
        past = -11
        for i in range(index, N):
            if nums[i] == past:
                continue
            past = nums[i]
            subsets.append(nums[i])
            self.dfs(result, nums, subsets, N, i+ 1)
            subsets.pop()
