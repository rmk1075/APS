import copy
import sys

n = m = 0
arr = []
virusList = []
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
maxVal = 0

def getSafeArea(copyed):
    safe = 0
    for i in range(n):
        for j in range(m):
            if copyed[i][j] == 0:
                safe += 1
    return safe

def spraedVirus(x, y, copyed):
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx and nx < n and 0 <= ny and ny < m:
            if copyed[nx][ny] == 0:
                copyed[nx][ny] = 2
                spraedVirus(nx, ny, copyed)

def setWall(start, depth):
    global maxVal

    if depth == 3:
        # 복사
        copyed = copy.deepcopy(arr)

        length = len(virusList)
        for i in range(length):
            [virusX, virusY] = virusList[i]
            spraedVirus(virusX, virusY, copyed)

        maxVal = max(maxVal, getSafeArea(copyed))
        return

    for i in range(start, n*m):
        x = (int)(i / m)
        y = (int)(i % m)

        if arr[x][y] == 0:
            arr[x][y] = 1
            setWall(i + 1, depth + 1)
            arr[x][y] = 0

if __name__ == '__main__':
    n, m = map(int, sys.stdin.readline().split())
    for i in range(n):
        arr.append(list(map(int, sys.stdin.readline().split())))

    for i in range(n):
        for j in range(m):
            if arr[i][j] == 2:
                virusList.append([i, j])

    setWall(0, 0)
    print(maxVal)

# import copy

# N, M = map(int, input().strip().split())
# field = [list(map(int, input().split())) for _ in range(N)]
# dx, dy = (-1, 1, 0, 0), (0, 0, -1, 1)
# answer = 0

# virus = list()
# for i in range(N):
#     for j in range(M):
#         if field[i][j] == 2:
#             virus.append((i, j))

# def polluted_field(board, temp_virus):
#     while temp_virus:
#         x, y = temp_virus.pop()

#         for i in range(4):
#             nx, ny = x+dx[i], y+dy[i]
#             if 0 <= nx and nx < N and 0 <= ny and ny < M:
#                 if board[nx][ny] == 0:
#                     board[nx][ny] = 2
#                     temp_virus.append((nx, ny))
    
#     val = 0
#     for i in range(N):
#         val += board[i].count(0)

#     return val

# def setWall(x, y, board, w):
#     if w == 3:
#         # print(board)
#         result = polluted_field(copy.deepcopy(board), copy.deepcopy(virus))
#         global answer
#         answer = max(answer, result)
#         return

#     for i in range(N):
#         for j in range(M):
#             if board[i][j] == 0:
#                 board[i][j] = 1
#                 setWall(i, j, copy.deepcopy(board), w+1)
#                 board[i][j] = 1

# for i in range(N):
#     for j in range(M):
#         setWall(i, j, field, 0)

# print(answer)
