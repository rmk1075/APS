import sys

K, N = map(int, sys.stdin.readline().split())
lines = [int(sys.stdin.readline()) for _ in range(K)]
ans = 0


def check(val):
    count = 0
    for line in lines:
        count += line // val
    return count


def search(left, right):
    while left <= right:
        mid = (left + right) // 2
        temp = check(mid)
        if N <= temp:
            global ans
            ans = max(ans, mid)
            left = mid + 1
        else:
            right = mid - 1


search(1, max(lines))
print(ans)
