class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        board, visited = ['.' * n for _ in range(n)], [[0 for _ in range(n)] for _ in range(n)]
        result = []
        for i in range(n):
            board[0] = board[0][:i] + 'Q' + board[0][i + 1:]
            self.check(n, 0, i, visited, 1)
            self.find(n, 1, i, board, visited, 2, result)
            self.check(n, 0, i, visited, -1)
            board[0] = board[0][:i] + '.' + board[0][i + 1:]

        return result

    def check(self, n: int, x: int, y: int, visited: List[List[int]], chk: int):
        for i in range(n):
            # 가로
            visited[x][i] += chk

            # 세로
            visited[i][y] += chk

            # 대각선
            if -1 < x - i and -1 < y - i:
                visited[x - i][y - i] += chk
            
            if -1 < x - i and y + i < n:
                visited[x - i][y + i] += chk
            
            if x + i < n and -1 < y - i:
                visited[x + i][y - i] += chk
            
            if x + i < n and y + i < n:
                visited[x + i][y + i] += chk

        visited[x][y] = chk if 0 < chk else 0
    
    def find(self, n: int, x: int, y: int, board: List[str], visited: List[List[int]], chk: str, result: List[List[str]]):
        if chk == n + 1:
            result.append(board[:])
            return
        
        for i in range(n):
            if visited[x][i] != 0:
                    continue
                    
            board[x] = board[x][:i] + 'Q' + board[x][i + 1:]
            self.check(n, x, i, visited, chk)
            self.find(n, x + 1, i, board, visited, chk + 1, result)
            self.check(n, x, i, visited, -chk)
            board[x] = board[x][:i] + '.' + board[x][i + 1:]