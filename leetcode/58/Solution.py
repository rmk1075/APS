class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        s = s.strip().split(' ')
        return 0 if len(s) == 0 else len(s[-1])

# class Solution:
#     def lengthOfLastWord(self, s: str) -> int:
#         s = s.strip()
#         n = len(s)

#         if n == 0:
#             return 0

#         i = n - 1
#         while -1 < i:
#             if s[i] == ' ':
#                 break
#             i -= 1
#         return n - i - 1