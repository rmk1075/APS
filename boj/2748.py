import sys

n = int(sys.stdin.readline())
fibolist = dict()
fibolist[0] = 0
fibolist[1] = 1

def fibo(n):
    if n in fibolist:
        return fibolist[n]
    else:
        fibolist[n] = fibo(n-1)+fibo(n-2)
        return fibolist[n]

print(fibo(n))