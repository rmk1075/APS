import sys

A = sys.stdin.readline().strip()
B = sys.stdin.readline().strip()
length = [0 for _ in range(len(A))]
length.insert(0, 0)

for b in B:
    temp = [0 for _ in range(len(A))]
    temp.insert(0,0)
    for i in range(len(A)):
        if b == A[i]:
            temp[i+1] = length[i]+1
        else:
            temp[i+1] = max(length[i+1], temp[i])
    length = temp

print(max(length))
