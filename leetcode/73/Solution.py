class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        m, n = len(matrix), len(matrix[0])
        row, col = [False for _ in range(m)], [False for _ in range(n)]
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == 0:
                    row[i] = True
                    col[j] = True
        
        for r in range(m):
            if row[r]:
                matrix[r] = [0 for _ in range(n)]
        
        for c in range(n):
            if col[c]:
                for r in range(m):
                    matrix[r][c] = 0