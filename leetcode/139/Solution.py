class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        N = len(s)
        dp = [True] + [False] * N
        wordDict = set(wordDict)
        for i in range(1, N + 1):
            for j in range(1, i + 1):
                dp[i] |= dp[j - 1] and (s[j - 1 : i] in wordDict)
                if dp[i]:
                    break
        return dp[N]