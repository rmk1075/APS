class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        x, y, d = 0, 0, 0
        dx, dy = [0, 1, 0, -1], [1, 0, -1, 0]
        board = [[0 for _ in range(n)] for _ in range(n)]
        for i in range(1, n**2 + 1):
            board[x][y] = i
            cx, cy = x + dx[d], y + dy[d]
            if cx < 0 or cx == n or cy < 0 or cy == n or board[cx][cy] != 0:
                d = (d + 1) % 4
                cx, cy = x + dx[d], y + dy[d]
            x, y = cx, cy
        
        return board