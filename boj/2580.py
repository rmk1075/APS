import sys

board = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(9)]

row = [[False for _ in range(10)] for __ in range(9)]
col = [[False for _ in range(10)] for __ in range(9)]
sec = [[False for _ in range(10)] for __ in range(9)]
sec_xy = [[3*(i//3) + j//3 for j in range(9)] for i in range(9)]
zeros = list()

for i in range(9):
    for j in range(9):
        if board[i][j] == 0:
            zeros.append((i,j))
            continue
        row[i][board[i][j]] = True
        col[j][board[i][j]] = True
        sec[sec_xy[i][j]][board[i][j]] = True
count = len(zeros)

def dfs(depth):
    if depth == count:
        for b in board:
            print(' '.join(list(map(str, b))))
        sys.exit(0)
    else:
        x, y = zeros[depth]
        for i in range(1, 10):
            if not (row[x][i] or col[y][i] or sec[sec_xy[x][y]][i]):
                row[x][i] = col[y][i] = sec[sec_xy[x][y]][i] = True
                board[x][y] = i
                dfs(depth+1)
                board[x][y] = 0
                row[x][i] = col[y][i] = sec[sec_xy[x][y]][i] = False
dfs(0)

# import sys
# from collections import deque

# def candidate(board, x, y):
#     candidates = [i for i in range(1, 10)]
#     for c in candidates:
#         if c in board[x] or c in board[:][y]:
#             candidates.remove(c)
    
#     for i in range(3*(x//3), 3*(x//3+1)):
#         for j in range(3*(y//3), 3*(y//3+1)):
#             if board[i][j] in candidates:
#                 candidates.remove(board[i][j])

#     return candidates

# def sdoku(board, queue):
#     if not queue:
#         for b in board:
#             print(' '.join(list(map(str, b))))
#         sys.exit(0)
    
#     while(queue):
#         x, y, = queue.pop()
#         candidates = candidate(board, x, y)
    
#         for c in candidates:
#             board[x][y] = c
#             sdoku(board, queue)
#             board[x][y] = 0

# if __name__ == "__main__":
#     board = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(9)]
#     queue = [[i, j] for i in range(9) for j in range(9) if board[i][j] == 0]
#     sdoku(board, queue)
