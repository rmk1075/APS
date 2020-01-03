import sys

N = int(sys.stdin.readline().strip())
P = sorted(list(map(int, sys.stdin.readline().strip().split())))
for i in range(1, N):
    P[i] += P[i-1]
print(sum(P))