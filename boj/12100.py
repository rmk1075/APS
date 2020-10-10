from collections import deque
N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]
ans, queue = 0, deque()

def get(x, y):
    if board[x][y]:
        queue.append(board[x][y])
        board[x][y] = 0

def merge(x, y, dx, dy):
    while queue:
        val = queue.popleft()
        if not board[x][y]:
            board[x][y] = val
        elif board[x][y] == val:
            board[x][y] = val*2
            x, y = x+dx, y+dy
        else:
            x, y = x+dx, y+dy
            board[x][y] = val

def move(k):
    if k == 0:
        for j in range(N):
            for i in range(N):
                get(i, j)
            merge(0, j, 1, 0)
    elif k == 1:
        for j in range(N):
            for i in range(N-1, -1, -1):
                get(i, j)
            merge(N-1, j, -1, 0)
    elif k == 2:
        for i in range(N):
            for j in range(N):
                get(i, j)
            merge(i, 0, 0, 1)
    else:
        for i in range(N):
            for j in range(N-1, -1, -1):
                get(i, j)
            merge(i, N-1, 0, -1)

def solve(count):
    global board, ans
    if count == 5:
        for i in range(N):
            ans = max(ans, max(board[i]))
        return
    
    field = [x[:] for x in board]
    for i in range(4):
        move(i)
        solve(count+1)
        board = [x[:] for x in field]

solve(0)
print(ans)

# N = int(input())
# board = [list(map(int, input().split())) for i in range(N)]
# dx, dy = (-1, 1, 0, 0), (0, 0, -1, 1)

# queue = list()
# queue.append(board)
# count = 0
# while count <= 5:
#     temp_field = queue.pop(0)[:]

#     for d in range(4):
#         start, dest, step = 0, N, 1
#         if d == 1 or d == 3:
#             start, dest, step = N-1, -1, -1

#         combined = [[0]*N for i in range(N)]
#         for i in range(start, dest, step):
#             for j in range(start, dest, step):
#                 x, y = i, j

#                 if temp_field[x][y] == 0:
#                     continue

#                 while 0 <= x+dx[d] and 0 <= y+dy[d] and x+dx[d] < N and y+dy[d] < N:
#                     if temp_field[x+dx[d]][y+dy[d]] == 0:
#                         temp_field[x+dx[d]][y+dy[d]] = temp_field[x][y]
#                         temp_field[x][y] = 0
#                     elif temp_field[x+dx[d]][y+dy[d]] == temp_field[x][y] and combined[x+dx[d]][y+dy[d]] == 0:
#                         temp_field[x+dx[d]][y+dy[d]] += temp_field[x][y]
#                         temp_field[x][y] = 0
#                         combined[x+dx[d]][y+dy[d]] = 1
#                         break
#                     else:
#                         break

#                     x += dx[d]
#                     y += dy[d]

#         queue.append(temp_field)
#     count += 1

# max_val = 0
# for field in queue:
#     if max_val < max(map(max, field)):
#         max_val = max(map(max, field))

# print(max_val)
