class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        if obstacleGrid[0][0] or obstacleGrid[m - 1][n - 1]:
            return 0

        board = [[0 for _ in range(n)] for _ in range(m)]
        board[0][0] = 1
        for i in range(1, m):
            if obstacleGrid[i][0]:
                break
            board[i][0] = 1
        for i in range(1, n):
            if obstacleGrid[0][i]:
                break
            board[0][i] = 1

        for i in range(1, m):
            for j in range(1, n):
                if obstacleGrid[i][j]:
                    continue
                board[i][j] = board[i - 1][j] + board[i][j - 1]
        
        return board[m - 1][n - 1]