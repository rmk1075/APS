class Solution(object):
    def largestRectangleArea(self, heights):
        stack = []  # stack[n] = (i, h): i(index), h(height)
        heights.append(-1)
        result, idx, height = 0, 0, 0
        for i, h in enumerate(heights):
            idx = i
            while stack and h < stack[-1][1]:
                idx, height = stack.pop()
                result = max(result, height * (i - idx))
            if not stack or stack[-1][1] < h:
                stack.append((idx, h))
        return result

# class Solution(object):
#     def largestRectangleArea(self, heights):
#         N = len(heights)
#         if N == 1:
#             return heights[0]

#         leftList, rightList = [0 for _ in range(N)], [0 for _ in range(N)]
#         leftList[0], rightList[N - 1] = -1, N
#         for i in range(1, N):
#             idx = i - 1
#             while idx != -1 and heights[i] <= heights[idx]:
#                 idx = leftList[idx]
#             leftList[i] = idx
        
#         for i in range(N - 2, -1, -1):
#             idx = i + 1
#             while idx != N and heights[i] <= heights[idx]:
#                 idx = rightList[idx]
#             rightList[i] = idx

#         result = 0
#         for i in range(N):
#             result = max(result, (rightList[i] - leftList[i] - 1) * heights[i])
#         return result
