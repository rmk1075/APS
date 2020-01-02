import sys

N = int(sys.stdin.readline())
meetings = sorted([list(map(int, sys.stdin.readline().strip().split())) for _ in range(N)], key = lambda x:(x[0], x[1]))

count = 0
start = 0
end = 0
for m in meetings:
    if end <= m[0]:
        count += 1
        start = m[0]
        end = m[1]
    elif m[1] <= end:
        start = m[0]
        end = m[1]

print(count)
# print(meetings)
