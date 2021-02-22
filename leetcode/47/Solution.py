class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        result, head, visited  = [], [], [False] * len(nums)
        for i in range(len(nums)):
            if nums[i] in head:
                continue
            
            head.append(nums[i])
            visited[i] = True
            self.dfs(1, nums, i, visited, result, [nums[i]])
            visited[i] = False
        
        return result
    
    def dfs(self, cnt: int, nums: List[int], index: int, visited: List[bool], result: List[List[int]], tempList: List[int]):
        if cnt == len(nums):
            result.append(tempList)
            return
        
        head = []
        for i in range(len(nums)):
            if visited[i] or nums[i] in head:
                continue
            
            head.append(nums[i])
            visited[i] = True
            self.dfs(cnt + 1, nums, i, visited, result, tempList + [nums[i]])
            visited[i] = False