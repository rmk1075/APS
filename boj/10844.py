import sys

N = int(sys.stdin.readline())

nums = [1 for _ in range(10)]
nums[0] = 0

for i in range(1, N):
    temp = [0 for _ in range(10)]
    for j in range(10):
        if j == 0:
            temp[0] += nums[1]
        elif j == 9:
            temp[9] += nums[8]
        else:
            temp[j] += nums[j-1]+nums[j+1]
    nums = temp

print(sum(nums)%1000000000)
