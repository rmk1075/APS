import sys
import operator

maxNum = -1000000000
minNum = 1000000000

def cal(ops, val, count):
    if count == len(nums):
        global maxNum, minNum
        if maxNum < val:
            maxNum = val
        if val < minNum:
            minNum = val
    else:
        for i in range(len(ops)):
            if ops[i] != 0:
                temp = int(operator[i](val, nums[count]))
                ops[i] -= 1
                cal(ops, temp, count+1)
                ops[i] += 1

N = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().strip().split()))
ops = [op for op in list(map(int, sys.stdin.readline().strip().split()))]
operator = {0: operator.add, 1: operator.sub, 2:operator.mul, 3:operator.truediv}

cal(ops, nums[0], 1)

print(maxNum)
print(minNum)