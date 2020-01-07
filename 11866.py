import sys

N, K = map(int, sys.stdin.readline().strip().split())
circle = [i for i in range(1, N+1)]

josephus = list()
index = K-1
while True:
    josephus.append(circle.pop(index))
    if not circle:
        break
    index = (index+K-1)%len(circle)

print('<'+', '.join(map(str,josephus))+'>')
