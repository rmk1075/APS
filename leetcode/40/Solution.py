class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        candidates.sort()
        result = list()
        self.dfs(candidates, target, result, 0, [])

        return result
    
    def dfs(self, candidates: List[int], target: int, result: List[List[int]], index: int, tempList:List[int]):
        for i in range(index, len(candidates)):
            num = candidates[i]

            if index < i and candidates[i - 1] == num:
                continue

            if target == num:
                result.append(tempList + [num])
                return
            
            if target < num:
                return
            
            self.dfs(candidates, target - num, result, i + 1, tempList + [num])