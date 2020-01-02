import sys

N, K = map(int, sys.stdin.readline().strip().split())
# P = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(N)]
capacity = [-1 for _ in range(K+1)]
capacity[0] = 0 # empty

for i in range(N):
    w, v = map(int, sys.stdin.readline().strip().split())
    for j in range(K-w, -1, -1):
        if capacity[j] == -1:
            continue
        capacity[j+w] = max(capacity[j+w], capacity[j]+v)
print(max(capacity))

# capacity = [[-1 for _ in range(K+1)] for _ in range(N+1)]
# for k in range(K+1):
#     capacity[0][k] = 0

# for i in range(1, N+1):
#     for j in range(K+1):
#         if j < P[i-1][0]:
#             capacity[i][j] = capacity[i-1][j]
#         else:
#             capacity[i][j] = max(capacity[i-1][j], capacity[i-1][j-P[i-1][0]]+P[i-1][1])

# print(max([max(v) for v in capacity]))
