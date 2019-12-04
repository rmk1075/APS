# i < 6: 1, 1, 1, 2, 2
# 6 <= i: num[i] = num[i-1] + num[i-5]
T = int(input())
for _ in range(T):
    N = int(input())
    padovan = [0, 1, 1, 1, 2, 2, 3]
    if N > 6:
        for n in range(6, N):
            padovan.append(padovan[n-4] + padovan[n])
    
    print(padovan[N])