import sys

N = int(sys.stdin.readline())
rings = list(map(int, sys.stdin.readline().strip().split()))

def gcd(a, b):
    if a < b:
        a, b = b, a
    if b == 0:
        return a
    if a%b == 0:
        return b
    return gcd(b, a%b)

for i in range(1, N):
    tmp = gcd(rings[0], rings[i])
    print(str(int(rings[0]/tmp))+'/'+str(int(rings[i]/tmp)))