import sys

n = int(sys.stdin.readline())
poles = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(n)]
poles.sort()
length = [0 for _ in range(n)]

for i in range(n):
    count = 1
    for j in range(i, -1, -1):
        if poles[j][1] < poles[i][1]:
            count = max(length[j]+1, count)
    length[i] = count

print(n-max(length))