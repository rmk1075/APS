import sys

N, M = map(int, sys.stdin.readline().split())
A = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
M, K = map(int, sys.stdin.readline().split())
B = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]

for n in range(N):
    for k in range(K):
        val = 0

        for m in range(M):
            val += A[n][m]*B[m][k]

        sys.stdout.write(str(val)+' ')
        # print(val, end=' ')
    sys.stdout.write('\n')
    # print()