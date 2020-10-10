import sys

a, b = map(int, sys.stdin.readline().strip().split())

if a == b:
    print(a)
    print(a)
    sys.exit(0)
elif b < a:
    temp = b
    b = a
    a = temp

for i in range(a, 0, -1):
    if a%i == 0 and b%i == 0:
        print(i)
        break

print(int(a*b/i))
