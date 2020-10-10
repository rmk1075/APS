import sys

N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().strip().split()))
A_ = [0 for _ in range(N)]
A_[0] = 1

for i in range(N):
    count = 1
    for j in range(i, -1, -1):
        if A[j] < A[i]:
            count = max(A_[j]+1, count)
    A_[i] = count

print(max(A_))