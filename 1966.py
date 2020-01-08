import sys
from collections import deque

for _ in range(int(sys.stdin.readline())):
    N, M = map(int, sys.stdin.readline().strip().split())
    queue = deque(list(map(int, sys.stdin.readline().strip().split())))

    count = 0
    while queue:
        if queue[0] == max(queue):
            queue.popleft()
            count += 1
            if M == 0:
                break
        else:
            queue.rotate(-1)
        M -= 1
        if M < 0:
            M = len(queue)-1
    print(count)
