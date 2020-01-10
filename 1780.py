import sys

N = int(sys.stdin.readline())
paper = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
ans = [0, 0, 0]
def check(x, y, n):
    for i in range(x, x+n):
        for j in range(y, y+n):
            if paper[x][y] != paper[i][j]:
                return False
    return True

def dnc(x, y, n):
    global ans    
    if check(x, y, n):
        ans[paper[x][y]] += 1
    else:
        for i in range(3):
            for j in range(3):
                dnc(x+(i*(n//3)), y+(j*(n//3)), n//3)

dnc(0, 0, N)
for i in range(-1, 2):
    print(ans[i])
