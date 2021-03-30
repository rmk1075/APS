class Solution(object):
    def maximalRectangle(self, matrix):
        if len(matrix) == 0 or len(matrix[0]) == 0:
            return 0
        m, n = len(matrix), len(matrix[0])
        maxArea = 0
        dp = [[0 for _ in range(n)] for _ in range(m)]
        if matrix[0][0] == '1':
            dp[0][0] = maxArea = 1
        for j in range(1, n):
            dp[0][j] = 1 if matrix[0][j] == '1' else 0
            width = dp[0][j]
            for k in range(j, -1, -1):
                if width == 0:
                    break
                if dp[0][k] < width:
                    width = dp[0][k]
                maxArea = max(maxArea, width * (j - k + 1))
        for i in range(1, m):
            for j in range(n):
                dp[i][j] = dp[i - 1][j] + 1 if matrix[i][j] == '1' else 0
                width = dp[i][j]
                for k in range(j, -1, -1):
                    if width == 0:
                        break
                    if dp[i][k] < width:
                        width = dp[i][k]
                    maxArea = max(maxArea, width * (j - k + 1))
        return maxArea



# class Solution(object):
#     def maximalRectangle(self, matrix):
#         if len(matrix) == 0 or len(matrix[0]) == 0:
#             return 0

#         m, n = len(matrix), len(matrix[0])
#         maxArea = 0
#         dp = [[0 for _ in range(n)] for _ in range(m)]
#         for i in range(m):
#             for j in range(n):
#                 if i == 0:
#                     dp[i][j] = 1 if matrix[i][j] == '1' else 0
#                 else:
#                     dp[i][j] = dp[i - 1][j] + 1 if matrix[i][j] == '1' else 0
                
#                 width = dp[i][j]
#                 for k in range(j, -1, -1):
#                     if width == 0:
#                         break
#                     if dp[i][k] < width:
#                         width = dp[i][k]
#                     maxArea = max(maxArea, width * (j - k + 1))
#         return maxArea
