import sys

N = int(sys.stdin.readline())

ans = sys.maxsize
def cal(n, count):
    if n == 0:
        global ans
        ans = min(ans, count)
        return

    if n < 3:
        return
    elif n < 5:
        cal(n-3, count+1)
    else:
        cal(n-5, count+1)
        cal(n-3, count+1)
    
cal(N, 0)
if ans == sys.maxsize:
    print(-1)
else:
    print(ans)