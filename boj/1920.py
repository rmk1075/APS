import sys

N = int(sys.stdin.readline())
A = sorted(list(map(int, sys.stdin.readline().split())))
M = int(sys.stdin.readline())
Ms = list(map(int, sys.stdin.readline().split()))


def binary(left, right, num):
    while left <= right:
        mid = (left+right)//2
        if A[mid] == num:
            print(1)
            return

        if num < A[mid]:
            right = mid-1
        else:
            left = mid+1
    print(0)


for m in Ms:
    binary(0, N-1, m)