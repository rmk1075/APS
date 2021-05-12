class Solution:
    def partition(self, s: str) -> List[List[str]]:
        result = []
        self.recursion(s, [], result)
        return result
    
    def recursion(self, s: str, temp: List[str], result: List[List[str]]):
        if len(s) == 0:
            result.append(temp)
            return
        
        for i in range(1, len(s) + 1):
            if self.isPalindrome(s[:i]):
                self.recursion(s[i:], temp + [s[:i]], result)
    
    def isPalindrome(self, s: str) -> bool:
        N = len(s)
        if N == 1:
            return True

        for i in range(0, (N // 2) if (N % 2 == 0) else (N // 2 + 1)):
            if s[i] != s[N - i - 1]:
                return False
        return True