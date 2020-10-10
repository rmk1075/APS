import sys

n = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().strip().split()))
ans = [0 for _ in range(n)]
ans.insert(0,-1001)

for i in range(n):
    if nums[i] < nums[i] + ans[i-1]:
        ans[i] = nums[i] + ans[i-1]
    else:
        ans[i] = nums[i]

print(max(ans[:n]))
