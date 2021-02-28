class Solution:
    def totalNQueens(self, n: int) -> int:
        col, sumList, diffList = [False for _ in range(n)], [], []
        return self.dfs(n, 0, col, sumList, diffList)

    def dfs(self, n: int, row: int, col: List[bool], sumList: List[int], diffList: List[int]) -> int:
        if row == n:
            return 1

        count = 0
        for i in range(n):
            if col[i] or row + i in sumList or row - i in diffList:
                continue

            col[i] = True
            sumList.append(row + i)
            diffList.append(row - i)
            count += self.dfs(n, row + 1, col, sumList, diffList)
            col[i] = False
            sumList.pop()
            diffList.pop()

        return count