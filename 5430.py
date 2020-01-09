import sys

T = int(sys.stdin.readline())
for _ in range(T):
    P = list(map(str, sys.stdin.readline().strip()))
    n = int(sys.stdin.readline())
    x = sys.stdin.readline()
    if n == 0:
        x = list()
    else:
        x = list(map(int, x.replace("[", "").replace("]", "").strip().split(',')))

    if len(x) < P.count('D'):
        print('error')
    else:
        index = 0
        for p in P:
            if p == 'R':
                if index == 0:
                    index = -1
                else:
                    index = 0
            else:
                del x[index]
        if index == -1:
            print('['+','.join(map(str, reversed(x)))+']')
        else:
            print('['+','.join(map(str,x))+']')
