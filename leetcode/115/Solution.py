class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        s_len, t_len = len(s), len(t)
        if s_len < t_len:
            return 0

        dp = [[0 for _ in range(len(s))] for _ in range(len(t))]
        target = t[t_len - 1]
        if s[s_len - 1] == target:
            dp[t_len - 1][s_len - 1] = 1
        for i in range(s_len - 2, -1, -1):
            dp[t_len - 1][i] = dp[t_len - 1][i + 1] + 1 if s[i] == target else dp[t_len - 1][i + 1]

        for i in range(t_len - 2, -1, -1):
            target = t[i]
            for j in range(s_len + i - t_len, -1, -1):
                dp[i][j] = dp[i][j + 1] + dp[i + 1][j + 1] if s[j] == target else dp[i][j + 1]

        return dp[0][0]