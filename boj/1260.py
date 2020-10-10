import sys

N, M, V = map(int, sys.stdin.readline().split())
graph = [[0 for _ in range(N+1)] for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a][b] = 1
    graph[b][a] = 1

def dfs(num):
    print(num, end = ' ')
    global visited
    visited[num] = 1
    for n in range(len(graph[num])):
        if graph[num][n] == 1 and visited[n] == 0:
            dfs(n)

def bfs(num):
    queue = list()
    queue.append(num)

    while queue:
        n = queue.pop(0)
        print(n, end = ' ')
        global visited
        visited[n] = 1
        for i in range(len(graph[n])):
            if graph[n][i] == 1 and visited[i] == 0:
                queue.append(i)
                visited[i] = 1

visited = [0 for _ in range(N+1)]
dfs(V)

print()

visited = [0 for _ in range(N+1)]
bfs(V)
