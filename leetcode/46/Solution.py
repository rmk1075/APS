class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        length = len(nums)
        result = []
        for i in range(len(nums)):
            result += self.dfs(nums, length, 1, i, 1 << i, [nums[i]])
        
        return result
    
    def dfs(self, nums: List[int], length: int, cnt : int, index: int, visited: int, tempList: List[int]) -> List[List[int]]:
        if cnt == length:
            return [tempList]
        
        result = []
        for i in range(length):
            if (visited & (1 << i)) != 0:
                continue
            
            result += self.dfs(nums, length, cnt + 1, i, visited | (1 << i), tempList + [nums[i]])

        return result