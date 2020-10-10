import sys

n, m = map(int, sys.stdin.readline().strip().split())

def cal(val, d):
    ans = 0
    while val != 0:
        val = val//d
        ans += val
    return ans    
if m == 0:
    print(0)
else:
    print(min(cal(n,5)-cal(n-m,5)-cal(m,5), cal(n,2)-cal(n-m,2)-cal(m,2)))
