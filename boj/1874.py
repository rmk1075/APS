import sys

n = int(sys.stdin.readline())
lastAppended = 0
stack = [0]
ans = str()
for _ in range(n):
    temp = int(sys.stdin.readline())
    if temp < lastAppended:
        if temp == stack.pop():
            ans += '-\n'
        else:
            print('NO')
            sys.exit(0)
    else:
        for num in range(lastAppended+1, temp+1):
            stack.append(num)
            ans += '+\n'
        lastAppended = temp
        stack.pop()
        ans += '-\n'

print(ans)
