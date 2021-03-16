class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)

        if not m or not n:
            return max(m, n)

        board = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
        for i in range(1, m + 1):
            board[i][0] = i
        for i in range(1, n + 1):
            board[0][i] = i

        for i in range(m):
            for j in range(n):
                board[i + 1][j + 1] = board[i][j] if word1[i] == word2[j] else min(board[i][j], board[i + 1][j], board[i][j + 1]) + 1
        
        return board[m][n]