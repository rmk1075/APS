# x + km = y + kn이 되어야 한다 - 공배수를 이용한 방법
def run(m,n,x,y):
    while x <= m*n:
        if (x-y)%n == 0:
            return x
        x += m
    return -1

T = int(input())
for _ in range(T):
    m, n, x, y = map(int, input().split())
    print(run(m,n,x,y))
