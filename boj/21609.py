dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]

def findBlockGroups(N, board):
    block_groups, queue = [], []
    visited = [[False for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if board[i][j] < 1 or visited[i][j]:
                continue
            visited[i][j] = True
            for k in range(N):
                for l in range(N):
                    if visited[k][l] and board[k][l] == 0:
                        visited[k][l] = False

            color, cnt_sum, cnt_rainbow = board[i][j], 1, 0
            queue.append((i, j))
            while queue:
                current = queue.pop(0)
                for d in range(4):
                    x, y = current[0] + dx[d], current[1] + dy[d]
                    if x < 0 or x == N or y < 0 or y == N or visited[x][y] or not (board[x][y] == color or board[x][y] == 0):
                        continue
                    visited[x][y] = True
                    cnt_sum += 1
                    if board[x][y] == 0:
                        cnt_rainbow += 1
                    queue.append((x, y))
            if 1 < cnt_sum:
                block_groups.append((i, j, cnt_sum, cnt_rainbow))
    return block_groups

def findTargetGroup(N, block_groups):
    x, y, cnt_sum, cnt_rainbow = N, N, -1, -1
    for block_group in block_groups:
        x_, y_, cnt_sum_, cnt_rainbow_ = block_group
        if cnt_sum < cnt_sum_:
            x, y, cnt_sum, cnt_rainbow = block_group
        elif cnt_sum == cnt_sum_:
            if cnt_rainbow <= cnt_rainbow_:
                x, y, cnt_sum, cnt_rainbow = block_group

    return x, y, cnt_sum, cnt_rainbow

def removeGroup(N, board, block_group):
    i, j = block_group[0], block_group[1]
    color = board[i][j]
    visited = [[False for _ in range(N)] for _ in range(N)]
    board[i][j], visited[i][j] = -2, True
    queue = [(i, j)]
    while queue:
        i, j = queue.pop(0)
        for d in range(4):
            x, y = i + dx[d], j + dy[d]
            if x < 0 or x == N or y < 0 or y == N or visited[x][y] or not (board[x][y] == 0 or board[x][y] == color):
                continue
            board[x][y], visited[x][y] = -2, True
            queue.append((x, y))

def drop(N, board):
    for i in range(N - 2, -1, -1):
        for j in range(N):
            if board[i][j] < 0 or board[i + 1][j] != -2:
                continue
            x = i + 1
            while x + 1 < N and board[x + 1][j] == -2:
                x += 1
            board[x][j], board[i][j] = board[i][j], -2

def rotate(N, board):
    temp = [[None for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            temp[N - 1 - j][i] = board[i][j]
    return temp

N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
result = 0
while True:
    block_groups = findBlockGroups(N, board)

    if len(block_groups) == 0:
        break
    
    block_group = findTargetGroup(N, block_groups)
    result += block_group[2] ** 2
    removeGroup(N, board, block_group)
    drop(N, board)
    board = rotate(N, board)
    drop(N, board)

print(result)