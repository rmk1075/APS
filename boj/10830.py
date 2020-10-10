import sys

N, B = map(int, sys.stdin.readline().split())
A = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

def matrixCal(m1, m2):
    result = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            for k in range(N):
                result[i][j] += m1[i][k]*m2[k][j]
            if result[i][j] != 1000:
                result[i][j] %= 1000
    return result

def cal(n):
    if n == 1:
        return A

    if n%2 == 0:
        temp = cal(n/2)
        return matrixCal(temp, temp)
    else:
        temp = cal(n//2)
        return matrixCal(temp, matrixCal(temp, A))

matrix = cal(B)
for i in range(N):
    for j in range(N):
        sys.stdout.write(str(matrix[i][j]%1000)+' ')
    sys.stdout.write('\n')
