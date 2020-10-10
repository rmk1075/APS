import sys

fibolist = dict()
fibolist[0] = [1,0]
fibolist[1] = [0,1]

def fibo(n):
    if n in fibolist:
        return fibolist[n]
    else:
        fibolist[n] = [fibo(n-1)[0]+fibo(n-2)[0], fibo(n-1)[1]+fibo(n-2)[1]]
        return fibolist[n]

T = int(sys.stdin.readline())
while 0 < T:
    N = int(sys.stdin.readline())

    ans = fibo(N)
    print(ans[0], ans[1])

    T -= 1
