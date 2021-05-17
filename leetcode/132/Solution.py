class Solution:
    def minCut(self, s: str) -> int:
        N = len(s)
        cnt, dp = [0 for _ in range(N)], [[False for _ in range(N)] for _ in range(N)]
        for right in range(N):
            minCnt = right
            for left in range(right + 1):
                if s[left] == s[right] and (right - left < 2 or dp[left + 1][right - 1]):
                    dp[left][right] = True
                    minCnt = 0 if left == 0 else min(minCnt, cnt[left - 1] + 1)
            cnt[right] = minCnt
        return cnt[N - 1]