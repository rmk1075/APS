class Solution:
    def partition(self, s: str) -> List[List[str]]:
        N = len(s)
        result, dp = [], [[False for _ in range(N)] for _ in range(N)]
        self.dfs(s, N, 0, [], result, dp)

        return result
    
    def dfs(self, s: str, N: int, left: int, temp: List[str], result: List[List[str]], dp: List[List[bool]]):
        if left == N:
            result.append(temp)
            return
        
        for right in range(left, N):
            if s[left] == s[right] and (right - left <= 2 or dp[left + 1][right - 1]):
                dp[left][right] = True
                self.dfs(s, N, right + 1, temp + [s[left:right + 1]], result, dp)

# class Solution:
#     def partition(self, s: str) -> List[List[str]]:
#         result = []
#         self.recursion(s, [], result)
#         return result
    
#     def recursion(self, s: str, temp: List[str], result: List[List[str]]):
#         if len(s) == 0:
#             result.append(temp)
#             return
        
#         for i in range(1, len(s) + 1):
#             if self.isPalindrome(s[:i]):
#                 self.recursion(s[i:], temp + [s[:i]], result)
    
#     def isPalindrome(self, s: str) -> bool:
#         N = len(s)
#         if N == 1:
#             return True

#         for i in range(0, (N // 2) if (N % 2 == 0) else (N // 2 + 1)):
#             if s[i] != s[N - i - 1]:
#                 return False
#         return True