import sys

N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().strip().split()))
left = [0 for _ in range(N)]
right = [0 for _ in range(N)]

for i in range(N):
    count = 1
    for j in range(i, -1, -1):
        if A[j] < A[i]:
            count = max(left[j]+1, count)
    left[i] = count

for i in range(N-1, -1, -1):
    count = 1
    for j in range(N-1, i, -1):
        if A[j] < A[i]:
            count = max(right[j]+1, count)
    right[i] = count

A_ = [left[i]+right[i] for i in range(N)]
print(max(A_)-1)
