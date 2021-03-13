class Solution:
    def climbStairs(self, n: int) -> int:
        stairs = [0 for _ in range(n + 1)]
        stairs[0] = 1
        for i in range(n - 1):
            stairs[i + 1] += stairs[i]
            stairs[i + 2] += stairs[i]

        return stairs[n] + stairs[n - 1]