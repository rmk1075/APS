n = int(input())
stairs = []

for _ in range(n):
    stairs.append(int(input()))

stairs.reverse()
for i in range(n):
    if i == n-1