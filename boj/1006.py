import sys

def location(N, x, y):
    if x == -1:
        x = 1
    elif x == 2:
        x = 0
    
    if y == -1:
        y = N - 1
    elif y == N:
        y = 0
    
    return x, y

T = int(sys.stdin.readline())
for _ in range(T):
    N, W = map(int, sys.stdin.readline().split())
    board = []
    board.append(list(map(int, sys.stdin.readline().split())))
    board.append(list(map(int, sys.stdin.readline().split())))

