class Solution:
    def countDigitOne(self, n: int) -> int:
        result = 0
        m = 1
        while m <= n:
            div = m * 10
            result += (int(n / div)) * m + min(max(n % div - m + 1, 0), m)
            m *= 10
        return result
