import sys

n = int(sys.stdin.readline())
glasses = [int(sys.stdin.readline()) for _ in range(n)]
glasses.insert(0, 0)
grapes = [0 for _ in range(n+1)]

for i in range(1, n+1):
    if i == 1:
        grapes[1] = glasses[1]
    elif i == 2:
        grapes[2] = glasses[1]+glasses[2]
    else:
        grapes[i] = max(grapes[i-3]+glasses[i-1]+glasses[i], grapes[i-2]+glasses[i], grapes[i-1])

print(max(grapes))
