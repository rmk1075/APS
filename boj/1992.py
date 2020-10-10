import sys

N = int(sys.stdin.readline())
img = [list(sys.stdin.readline().strip()) for _ in range(N)]

def check(x, y, n):
    val = img[x][y]
    for i in range(x, x+n):
        for j in range(y, y+n):
            if img[i][j] != val:
                return 2
    return val

def dnc(x, y, n):
    ans = ''
    c = check(x, y, n)
    if c == '0':
        ans = '0'
    elif c == '1':
        ans = '1'
    else:
        ans += dnc(x, y, int(n/2))
        ans += dnc(x, int(y+n/2), int(n/2))
        ans += dnc(int(x+n/2), y, int(n/2))
        ans += dnc(int(x+n/2), int(y+n/2), int(n/2))
        ans = '(' + ans + ')'

    return ans

print(dnc(0, 0, N))