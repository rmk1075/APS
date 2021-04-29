class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        result = [[1]]
        for i in range(1, numRows):
            result.append([0 for _ in range(i + 1)])
            for j in range(i):
                val = result[i - 1][j]
                result[i][j] += val
                result[i][j + 1] += val      
        return result
