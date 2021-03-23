class Solution(object):
    def exist(self, board, word):
        m, n = len(board), len(board[0])
        visited = [[False for _ in range(n)] for _ in range(m)]

        l = len(word)
        if m * n < l:
            return False

        dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]
        for i in range(m):
            for j in range(n):
                if board[i][j] == word[0]:
                    visited[i][j] = True
                    if self.dfs(board, visited, dx, dy, m, n, word, l, 1, i, j):
                        return True
                    visited[i][j] = False
        return False

    def dfs(self, board, visited, dx, dy, m, n, word, l, idx, x, y):
        if idx == l:
            return True

        w = word[idx]
        for d in range(4):
            cx, cy = x + dx[d], y + dy[d]
            if cx == -1 or cx == m or cy == -1 or cy == n or visited[cx][cy] or board[cx][cy] != w:
                continue
            visited[cx][cy] = True
            if self.dfs(board, visited, dx, dy, m, n, word, l, idx + 1, cx, cy):
                return True
            visited[cx][cy] = False