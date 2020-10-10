import sys

K = int(sys.stdin.readline())
stack = list()
for _ in range(K):
    temp = int(sys.stdin.readline())
    if temp == 0:
        stack.pop()
    else:
        stack.append(temp)
print(sum(stack))
