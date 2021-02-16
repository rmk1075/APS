class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        candidates.sort()
        result = []
        self.dfs(result, candidates, target, 0, 0, [])

        return result
    
    def dfs(self, result: List[List[int]],candidates: List[int], target: int, index: int, value: int, tempList: List[int]):
        temp = tempList[:]

        for i in range(index, len(candidates)):
            num = candidates[i]
            if target == value + num:
                temp.append(num)
                result.append(temp)
                return
            
            if target < value + num:
                return
            
            temp.append(num)
            self.dfs(result, candidates, target, i, value + num, temp)
            temp.pop()