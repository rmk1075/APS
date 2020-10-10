import sys

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    ans = [0 for _ in range(10001)]

    for _ in range(N):
        ans[int(sys.stdin.readline())] += 1
    
    for i in range(10001):
        for _ in range(ans[i]):
            print(i)

## time limit
# import sys

# if __name__ == "__main__":
#     N = int(sys.stdin.readline())
#     numbers = dict()

#     for _ in range(N):
#         n = int(sys.stdin.readline())
#         if n in numbers:
#             numbers[n] = numbers[n]+1
#         else:
#             numbers[n] = 1
 
#     for k in sorted(numbers.keys()):
#         for _ in range(numbers[k]):
#             print(k)

# import sys

## memory??
# def sorting(ans, num):
#     mid = int(len(ans)/2)
#     while 0 <= mid and mid < len(ans):
#         if ans[mid] == num:
#             ans.insert(mid, num)
#             return ans

#         if ans[mid] < num:
#             if mid == len(ans)-1:
#                 ans.append(num)
#                 return ans
#             if num < ans[mid+1]:
#                 ans.insert(mid+1, num)
#                 return ans
#             mid = int((mid+len(ans)/2))
#         else:
#             if mid == 0:
#                 ans.insert(0, num)
#                 return ans
#             if ans[mid-1] < num:
#                 ans.insert(mid, num)
#                 return ans
#             mid = int(mid/2)

# if __name__ == "__main__":
#     N = int(sys.stdin.readline())
#     ans = list()
    
#     ans.append(int(sys.stdin.readline()))
#     for _ in range(N):
#         ans = sorting(ans, int(sys.stdin.readline()))
    
#     print('\n'.join(ans))
