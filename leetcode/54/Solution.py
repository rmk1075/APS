class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        result, dx, dy = [], [0, 1, 0, -1], [1, 0, -1, 0]
        m, n = len(matrix), len(matrix[0])
        cnt, limit, x, y, d = 0, m * n, 0, 0, 0
        while True:
            result.append(matrix[x][y])
            matrix[x][y] = 101
            cnt += 1

            if cnt == m * n:
                break

            cx, cy = x + dx[d], y + dy[d]
            while cx < 0 or cx == m or cy < 0 or cy == n or matrix[cx][cy] == 101:
                d = (d + 1) % 4
                cx, cy = x + dx[d], y + dy[d]
            x, y = cx, cy
            
        return result