import sys

N = int(sys.stdin.readline())
nums = sorted([int(sys.stdin.readline()) for _ in range(N)], reverse=True)

for i in range(1, N):
    nums[i-1] = nums[i-1]-nums[i]
nums.pop()
nums.sort()

gcd = nums[0]
if len(nums) != 1:
    for i in range(1, len(nums)):
        x, y = gcd, nums[i]
        mod = x%y
        while 0 < mod:
            x = y
            y = mod
            mod = x%y
        gcd = y

divs = [gcd]
for i in range(2, int(gcd**(1/2)+1)):
    if gcd%i == 0:
        divs.append(i)
        if gcd//i != i:
            divs.append(gcd//i)
divs.sort()

for d in divs:
    print(d, end=' ')

# for i in range(2, int(gcd/2)+1):
#     if gcd%i == 0:
#         print(i, end=' ')
# print(gcd)
