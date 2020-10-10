import sys

N = int(sys.stdin.readline())
paper = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

def check(x, y, n):
    val = paper[x][y]

    for i in range(x, x+n):
        for j in range(y, y+n):
            if paper[i][j] != val:
                return 2
    return val

def func(x, y, n):
    white, blue = 0, 0
    c = check(x, y, n)
    if c == 0:
        white += 1
    elif c == 1:
        blue += 1
    else:
        white1, blue1 = func(x, y, int(n/2))
        white2, blue2 = func(x, int(y+n/2), int(n/2))
        white3, blue3 = func(int(x+n/2), y, int(n/2))
        white4, blue4 = func(int(x+n/2), int(y+n/2), int(n/2))
        
        white += white1+white2+white3+white4
        blue += blue1+blue2+blue3+blue4

    return white, blue

w, b = func(0, 0, N)
print(w)
print(b)