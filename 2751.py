import sys

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    numbers = sorted(list(map(int, sys.stdin.read().splitlines())))
    print('\n'.join(map(str, numbers)))

# import sys

# if __name__ == "__main__":
#     N = int(sys.stdin.readline())
#     numbers = sorted(list(int, sys.stdin.read().splitlines()))
#     print('\n'.join(map(str, numbers)))

# merge sort
# def merge(left, right):
#     sorted = list()
#     i, j = 0,0
#     while i < len(left) and j < len(right):
#         if left[i] < right[j]:
#             sorted.append(left[i])
#             i += 1
#         else:
#             sorted.append(right[j])
#             j += 1
    
#     if i == len(left):
#         sorted.append(right[j:])
#     if j == len(right):
#         sorted.append(left[i:])
    
#     return sorted

# def mergeSort(nums):
#     if len(nums) <= 1:
#         return nums

#     mid = int(len(nums)/2)
#     left = mergeSort(nums[0:mid])
#     right = mergeSort(nums[mid:])

#     return merge(left, right)

# if __name__ == "__main__":
#     numbers = [int(input().strip()) for _ in range(int(input().strip()))]

#     ans = merge(numbers)
#     print('\n'.join(ans))

## the source code followed is passed when be judged by the Pypy3 language.
# if __name__ == "__main__":
#     N = int(input().strip())
#     numbers = [int(input().strip()) for _ in range(N)]
#     numbers.sort()

#     for n in numbers:
#         print(n)
