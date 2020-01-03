import sys

N = int(sys.stdin.readline())
factors = sorted(list(map(int, sys.stdin.readline().strip().split())))
print(factors[0]*factors[N-1])

#factors = list(map(int, sys.stdin.readline().strip().split()))

# print(min(factors)*max(factors))
