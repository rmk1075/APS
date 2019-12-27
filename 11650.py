import sys

if __name__ == "__main__":
    N = int(sys.stdin.readline())

    points = list()
    for _ in range(N):
        points.append(list(map(int, sys.stdin.readline().strip().split())))
    
    for p in sorted(points, key=lambda l: (l[0], l[1])):
        print(p[0], p[1])

