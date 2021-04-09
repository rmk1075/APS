class Solution:
    def numTrees(self, n: int) -> int:
        dd = {}
        for i in range(n + 1):
            dd[i] = {}
            dd[i][i] = 1
        return self.count(0, n, dd)
    
    def count(self, left: int, right: int, dd: Dict[int, Dict[int, int]]) -> int:
        if right in dd[left].keys():
            return dd[left][right]
        cnt = 0
        for i in range(left, right):
            cnt += self.count(left, i, dd) * self.count(i + 1, right, dd)
        dd[left][right] = cnt
        return cnt