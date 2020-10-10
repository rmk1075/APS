N, X = map(int, input().split())
A = list(input().split())
for i in range(len(A)):
    if(int(A[i]) < X):
        print(A[i],'', end = "")
