class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        lens = len(s)
        if len(p) - p.count('*') > len(s):
            return False
        
        dp = [True] + (lens * [False])
        for i in p:
            if i == '*':
                for j in range(1, lens + 1):
                    dp[j] = dp[j - 1] or dp[j]
            else:
                for j in reversed(range(lens)):
                    dp[j + 1] = dp[j] and (i == s[j] or i == '?')
                
            dp[0] = dp[0] and i == '*'

        return dp[-1]

# class Solution:
#     def isMatch(self, s: str, p: str) -> bool:

#         if len(p) - p.count('*') > len(s):
#             return False

#         p = self.comp(p)
#         return self.check(s, p, len(s), len(p))
    
#     def comp(self, p:str) -> str:
#         result = str()
#         index = 0
#         while index < len(p):
#             if p[index] != '*':
#                 result += p[index]
#                 index += 1
#             else:
#                 result += '*'
#                 while index < len(p) and p[index] == '*':
#                     index += 1
#         return result
    
#     def check(self, s: str, p: str, lens: int, lenp: int) -> bool:
#         if lens == 0 or lenp == 0:
#             if lens != 0:
#                 return False

#             if lenp != 0:
#                 for _ in p:
#                     if _ != '*':
#                         return False
            
#             return True
        
#         if p[0] == '*':
#             return self.check(s[1:], p[1:], lens - 1, lenp - 1) or self.check(s[1:], p[:], lens - 1, lenp) or self.check(s[:], p[1:], lens, lenp - 1)
#         elif p[0] == '?' or p[0] == s[0]:
#             return self.check(s[1:], p[1:], lens - 1, lenp - 1)
#         else:
#             return False