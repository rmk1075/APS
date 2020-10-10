from sys import stdin
from collections import deque
input = stdin.readline

n, m = map(int, input().split())
a = [list(input().strip()) for _ in range(n)]
check = [[[[False]*m for _ in range(n)] for _ in range(m)] for _ in range(n)]
dx, dy = (-1, 0, 1, 0), (0, 1, 0, -1)
q = deque()


def init():
    _rx, _ry, _bx, _by = [0]*4
    for i in range(n):
        for j in range(m):
            if a[i][j] == 'R':
                _rx, _ry = i, j
            elif a[i][j] == 'B':
                _bx, _by = i, j
    q.append((_rx, _ry, _bx, _by, 0))
    check[_rx][_ry][_bx][_by] = True


def move(_x, _y, _dx, _dy, _c):
    while a[_x+_dx][_y+_dy] != '#' and a[_x][_y] != 'O':
        _x += _dx
        _y += _dy
        _c += 1
    return _x, _y, _c


def bfs():
    while q:
        rx, ry, bx, by, d = q.popleft()
        if d >= 10:
            break
        for i in range(4):
            nrx, nry, rc = move(rx, ry, dx[i], dy[i], 0)
            nbx, nby, bc = move(bx, by, dx[i], dy[i], 0)
            if a[nbx][nby] == 'O':
                continue
            if a[nrx][nry] == 'O':
                print(d+1)
                return
            if nrx == nbx and nry == nby:
                if rc > bc:
                    nrx, nry = nrx-dx[i], nry-dy[i]
                else:
                    nbx, nby = nbx-dx[i], nby-dy[i]
            if not check[nrx][nry][nbx][nby]:
                check[nrx][nry][nbx][nby] = True
                q.append((nrx, nry, nbx, nby, d+1))
    print(-1)


init()
bfs()

# import sys

# N, M = map(int, input().split())
# field = [list(input().strip()) for _ in range(N)]
# visited = [[[[False]*M for _ in range(N)] for _ in range(M)] for _ in range(N)]

# def move(x, y, x_, y_, count):
#     while field[x+x_][y+y_] != '#' and field[x][y] != 'O':
#         x += x_
#         y += y_
#         count += 1
    
#     return x, y, count

# # top, bottom, left, right
# dx, dy = (-1, 1, 0, 0), (0, 0, -1, 1)

# # rx, ry, dx, dy, count
# queue = list()

# red = [0,0]
# blue = [0,0]
# for i in range(N):
#     for j in range(M):
#         if field[i][j] == 'R':
#             red[0], red[1] = i, j
#         if field[i][j] == 'B':
#             blue[0], blue[1] = i, j

# visited[red[0]][red[1]][blue[0]][blue[1]] = True
# queue.append([red[0], red[1], blue[0], blue[1], 0])

# while queue:
#     # print(queue)

#     rx, ry, bx, by, count = queue.pop(0)
    
#     if 10 < count:
#         break
    
#     # four directions
#     for i in range(4):
#         rx_, ry_, rc = move(rx, ry, dx[i], dy[i], 0)
#         bx_, by_, bc = move(bx, by, dx[i], dy[i], 0)
        
#         if field[bx_][by_] == 'O':
#             continue
        
#         if field[rx_][ry_] == 'O':
#             print(count+1)
#             sys.exit()
        
#         if rx_ == bx_ and ry_ == by_:
#             if rc > bc:
#                 rx_, ry_ = rx_-dx[i], ry_-dy[i]
#             else:
#                 bx_, by_ = bx_-dx[i], by_-dy[i]
        
#         if not visited[rx_][ry_][bx_][by_]:
#             visited[rx_][ry_][bx_][by_] = True
#             queue.append([rx_, ry_, bx_, by_, count+1])

# print(-1)
