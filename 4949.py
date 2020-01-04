import sys

while True:
    sentence = sys.stdin.readline()
    if sentence == '.\n':
        break
    else:
        sentence = list(sentence)
        stack = list()
        check = True

        for s in sentence:
            if s == '(' or s == '[':
                stack.append(s)
            elif s == ')':
                if not stack or stack.pop() != '(':
                    print('no')
                    check = False
                    break
            elif s == ']':
                if not stack or stack.pop() != '[':
                    print('no')
                    check = False
                    break
        if check:
            if stack:
                print('no')
            else:
                print('yes')