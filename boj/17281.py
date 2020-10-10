import sys
import itertools

N = int(sys.stdin.readline())
innings = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

order = [i for i in range(1, 9)]
permutation = itertools.permutations(order, 8)

maxVal = 0
for p in permutation:
    p = list(p)
    p.insert(3, 0)

    point, index = 0, 0
    for inning in innings:
        out = 0
        temp = [0, 0, 0]
        while out < 3:
            if inning[p[index]] == 0:
                out += 1
            elif inning[p[index]] == 1:
                point += temp[2]
                temp[2] = temp[1]
                temp[1] = temp[0]
                temp[0] = 1
            elif inning[p[index]] == 2:
                point += temp[2]+temp[1]
                temp[2] = temp[0]
                temp[1] = 1
                temp[0] = 0
            elif inning[p[index]] == 3:
                point += sum(temp)
                temp[2] = 1
                temp[1], temp[0] = 0, 0
            elif inning[p[index]] == 4:
                point += sum(temp)+1
                temp = [0, 0, 0]
            index += 1
            if 8 < index:
                index = 0
    maxVal = max(maxVal, point)
print(maxVal)
