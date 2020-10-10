import sys

N = int(sys.stdin.readline())
temp = 0
temp1 = 1
temp2 = 2

for i in range(N-1):
    temp = temp1
    temp1 = temp2
    temp2 = (temp+temp2)%15746

print(temp1)
