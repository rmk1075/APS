N = int(input())
words = []
for i in range(N):
    words.append(input())

count = 0
for word in words:
    count += 1
    w = []
    target = word[0]
    for i in word:
        if i not in w:
            w.append(i)
            target = i
        elif((i != target) and (i in w)):
            count -= 1
            break;
print(count)
