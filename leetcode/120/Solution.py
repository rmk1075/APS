class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        N = len(triangle)
        for i in range(1, N):
            triangle[i][0] += triangle[i - 1][0]
            triangle[i][i] += triangle[i - 1][i - 1]
            for j in range(1, i):
                    triangle[i][j] += min(triangle[i - 1][j - 1], triangle[i - 1][j])
        return min(triangle[N - 1])