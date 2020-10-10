import sys

N, K = map(int, sys.stdin.readline().strip().split())
combination = [[0 for _ in range(K+1)] for _ in range(N+1)]

for i in range(N+1):
    combination[i][0] = 1
for i in range(K+1):
    combination[i][i] = 1

for i in range(1, N+1):
    for j in range(1, K+1):
        combination[i][j] = combination[i-1][j] + combination[i-1][j-1]

print(int(combination[N][K]%10007))
