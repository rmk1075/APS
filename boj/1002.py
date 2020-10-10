T = int(input())
for _ in range(T):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())
    r = (r1+r2)**2
    distance = (x1-x2)**2 + (y1-y2)**2

    if distance==0:
        if r1==r2:
            print(-1)
        else:
            print(0)
    else:
        if r < distance:
            print(0)
        elif r==distance:
            print(1)
        else:
            if (r1-r2)**2==distance:
                print(1)
            elif (r1-r2)**2 > distance:
                print(0)
            else:
                print(2)
