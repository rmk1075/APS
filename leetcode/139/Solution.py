class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        N = len(s)
        dp = [False] * (N + 1)
        dp[0] = True
        for i in range(1, N + 1):
            for j in range(i):
                print(dp[j - 1], s[j : i])
                dp[i] |= dp[j - 1] and (s[j : i] in wordDict)
        print(dp)
        return dp[N]