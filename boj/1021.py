import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())
candidates = list(map(int, sys.stdin.readline().split()))
queue = deque([0 for _ in range(N)])
for i in range(len(candidates)):
    queue[candidates[i]-1] = i+1

count = 0
for i in range(1, len(candidates)+1):
    direction = 0
    if queue[0] != i:
        if queue.index(i) < list(reversed(queue)).index(i)+1:
            direction = -1
        else:
            direction = 1
        while queue[0] != i:
            queue.rotate(direction)
            count += 1
    queue.popleft()

print(count)
