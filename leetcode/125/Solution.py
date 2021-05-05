class Solution:
    def isPalindrome(self, s: str) -> bool:
        N = len(s)
        left, right = 0, N - 1
        while left < right:
            while left < right and not (s[left].isalpha() or s[left].isnumeric()):
                left += 1
            while left < right and not (s[right].isalpha() or s[right].isnumeric()):
                right -= 1

            if right <= left:
                break
            
            if s[left].lower() != s[right].lower():
                return False
            left += 1
            right -= 1

        return True