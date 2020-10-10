import sys

T = int(sys.stdin.readline())
for _ in range(T):
    braces = list(sys.stdin.readline().strip())
    if braces[0] == ')':
        print('NO')
    else:
        stack = list()
        check = True
        for b in braces:
            if b == '(':
                stack.append(b)
            else:
                if not stack:
                    print('NO')
                    check = False
                    break
                else:
                    stack.pop()
        if check:
            if stack:
                print('NO')
            else:
                print('YES')