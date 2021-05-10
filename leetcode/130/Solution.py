class Solution:
    def solve(self, board: List[List[str]]) -> None:
        dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]
        M, N = len(board), len(board[0])
        visited = [[False for _ in range(N)] for _ in range(M)]
        for i in range(M):
            for j in (0, N - 1):
                if visited[i][j] or board[i][j] == 'X':
                    continue
                self.bfs(board, visited, dx, dy, M, N, i, j)
        for i in range(N):
            for j in (0, M - 1):
                if visited[j][i] or board[j][i] == 'X':
                    continue
                self.bfs(board, visited, dx, dy, M, N, j, i)
        
        for i in range(M):
            for j in range(N):
                if board[i][j] == 'O' and not visited[i][j]:
                    board[i][j] = 'X'

    def bfs(self, board: List[List[str]], visited: List[List[bool]], dx: List[int], dy: List[int], M: int, N: int, i: int, j: int) -> None:
        visited[i][j] = True
        queue = [(i, j)]
        while queue:
            current = queue.pop(0)
            for d in range(4):
                x, y = current[0] + dx[d], current[1] + dy[d]
                if x < 0 or y < 0 or x == M or y == N or visited[x][y] or board[x][y] == 'X':
                    continue
                visited[x][y] = True
                queue.append((x, y))
