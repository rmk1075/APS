class Solution:
    def numDecodings(self, s: str) -> int:
        if s[0] == '0':
            return 0
        if len(s) == 1:
            return 1
        N = len(s)
        dp = [0 for _ in range(N)]
        dp[0] = 1 if s[0] != '0' else 0
        if 1 < N:
            if s[1] == '0':
                dp[1] = 1 if int(s[0] + s[1]) < 27 else 0
            else:
                dp[1] = 2 if int(s[0] + s[1]) < 27 else 1
        if dp[1] == 0:
            return 0

        for i in range(2, N):
            a, b = (s[i - 1] == '0'), (s[i] == '0')
            if a or b:
                if a and b:
                    dp[i] = 0
                elif a:
                    dp[i] = dp[i - 1]
                else:
                    dp[i] = dp[i - 2] if int(s[i - 1] + s[i]) < 27 else 0
            else:
                dp[i] = dp[i - 1] + (dp[i - 2] if int(s[i - 1] + s[i]) < 27 else 0)
        return dp[N - 1]