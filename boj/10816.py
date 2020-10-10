import sys

N = int(sys.stdin.readline())
cardN = list(map(int, sys.stdin.readline().split()))
nums = dict()
for n in cardN:
    if n in nums.keys():
        nums[n] += 1
    else:
        nums[n] = 1

M = int(sys.stdin.readline())
cardM = list(map(int, sys.stdin.readline().split()))
for m in cardM:
    if m in nums.keys():
        print(nums[m], end=' ')
    else:
        print(0, end=' ')

# import sys

# N = int(sys.stdin.readline())
# cardN = sorted(list(map(int, sys.stdin.readline().split())))
# cardDict = dict()
# for n in cardN:
#     if n in cardDict.keys():
#         cardDict[n] += 1
#     else:
#         cardDict[n] = 1
# keys = list(cardDict.keys())

# M = int(sys.stdin.readline())
# cardM = list(map(int, sys.stdin.readline().split()))

# def binary(left, right, num):
#     while left <= right:
#         mid = (left+right)//2
#         if keys[mid] == num:
#             print(cardDict[keys[mid]], end=' ')
#             return

#         if num < keys[mid]:
#             right = mid-1
#         else:
#             left = mid+1
#     print(0, end=' ')

# for m in cardM:
#     binary(0, len(keys)-1, m)
