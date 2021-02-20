import sys
from collections import deque

N, M = map(int, sys.stdin.readline().strip().split())
rel = [set() for _ in range(N + 1)]
for _ in range(M):
    a, b = map(int, sys.stdin.readline().strip().split())
    rel[b].add(a)

maxCnt, maxList = 0, []
for i in range(1, N + 1):
    queue, visited = deque([i]), [False] * (N + 1)
    visited[i] = True
    cnt = 1

    while queue:
        current = queue.popleft()
        for r in rel[current]:
            if visited[r]:
                continue
            visited[r] = True
            queue.append(r)
            cnt += 1

    if maxCnt < cnt:
        maxCnt = cnt
        maxList = [i]
    elif maxCnt == cnt:
        maxList.append(i)

for num in maxList:
    print(num, end = ' ')