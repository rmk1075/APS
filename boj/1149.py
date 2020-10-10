import sys

N = int(sys.stdin.readline())
prices = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(N)]
ans = [[0,0,0] for _ in range(N)]

for i in range(N):
    if i == 0:
        ans[i] = prices[i]
    else:
        ans[i][0] = prices[i][0] + min(ans[i-1][1], ans[i-1][2])
        ans[i][1] = prices[i][1] + min(ans[i-1][0], ans[i-1][2])
        ans[i][2] = prices[i][2] + min(ans[i-1][0], ans[i-1][1])

print(min(ans[N-1]))
