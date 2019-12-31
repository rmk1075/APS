import sys

n = int(sys.stdin.readline())
stairs = [int(sys.stdin.readline()) for _ in range(n)]

if n < 3:
    print(sum(stairs))
    sys.exit(0)
    
points = list()
points.append(stairs[0])
points.append(stairs[1]+points[0])
points.append(max(points[0]+stairs[2], stairs[1]+stairs[2]))

for i in range(3, n):
    points.append(max(stairs[i]+stairs[i-1]+points[i-3], stairs[i]+points[i-2]))

print(points[-1])
