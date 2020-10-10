# pisano period
# https://en.wikipedia.org/wiki/Pisano_period
import sys

n = int(sys.stdin.readline())%(1000000//10*15)
fibo = [0, 1]
for i in range(2, 1000000//10*15):
    fibo.append((fibo[i-1]+fibo[i-2])%1000000)

print(fibo[n])
