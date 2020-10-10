import sys

N, K = map(int,sys.stdin.readline().strip().split())

numerator, denominator = 1, 1
for i in range(K):
    numerator *= N-i
    denominator  *= K-i
print(int(numerator/denominator))