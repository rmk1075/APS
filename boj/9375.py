import sys

for t in range(int(sys.stdin.readline())):
    n = int(sys.stdin.readline())
    categories = dict()
    for _ in range(n):
        temp = sys.stdin.readline().strip().split()
        if temp[1] in categories:
            categories[temp[1]] += 1
        else:
            categories[temp[1]] = 1
    ans = 1
    for c in categories:
        ans *= categories[c]+1
    
    print(ans-1)
