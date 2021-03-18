class Solution(object):
    def searchMatrix(self, matrix, target):
        m, n = len(matrix), len(matrix[0])
        i = 0
        while i < m - 1:
            if matrix[i][0] <= target and target < matrix[i + 1][0]:
                break
            i += 1
        
        if matrix[i][0] == target:
            return True
        
        if target < matrix[i][0] or matrix[i][n - 1] < target:
            return False
        
        left, right = 0, n
        while left < right:
            mid = (left + right) // 2
            val = matrix[i][mid]
            if val == target:
                return True
            elif val < target:
                left = mid + 1
            else:
                right = mid
        
        return False

# class Solution(object):
#     def searchMatrix(self, matrix, target):
#         m, n = len(matrix), len(matrix[0])
#         left, right = 0, m * n
#         while left < right:
#             mid = (left + right) // 2
#             val = matrix[mid // n][mid % n]
#             if val == target:
#                 return True
#             elif val < target:
#                 left = mid + 1
#             else:
#                 right = mid
#         return False