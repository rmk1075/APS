import sys

N = int(sys.stdin.readline())

temp = 2
while 1 < N:
    if N%temp == 0:
        print(temp)
        N /= temp
    else:
        temp += 1
