import sys

N = int(sys.stdin.readline())
ans = 0

while N:
    if N%5 == 0:
        ans += N//5
        break
    else:
        N -= 3
        ans += 1

    if N != 0 and N < 3:
        print(-1)
        sys.exit(0)
print(ans)