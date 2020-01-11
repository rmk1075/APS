import sys

N, K = map(int, sys.stdin.readline().split())

if K == 0:
    print(0)
else:
    val = 1
    for i in range(K, 0):
        val =  (val*(N-(K-i))/K)%1000000007
    print(val)