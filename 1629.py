import sys

A, B, C = map(int, sys.stdin.readline().split())
vals = dict()
vals[1] = A%C

def div(n):
    if n in vals.keys():
        return vals[n]
    else:
        vals[n] = (div(n//2) * div(n//2 + n%2))%C
        return vals[n]

print(div(B)%C)