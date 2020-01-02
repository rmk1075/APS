import sys

N, K = map(int, sys.stdin.readline().strip().split())
A = reversed([int(sys.stdin.readline().strip()) for _ in range(N)])

val, count = 0, 0
for a in A:
    if K < val+a:
        continue
    count += (K-val)//a
    val = K-(K-val)%a

print(count)
