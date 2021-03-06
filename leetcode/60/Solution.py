import math

class Solution:
    count = 0
    def getPermutation(self, n: int, k: int) -> str:
        for i in range(1, n + 1):
            cnt = math.factorial(n - 1)
            if k <= cnt:
                return self.permutation(n, k, 1, 1 << i, str(i))
            k -= cnt

    def permutation(self, n: int, k: int, length: int, visited: int, temp: str) -> str:
        if length == n:
            self.count += 1
            return temp if self.count == k else ''

        for i in range(1, n + 1):
            if visited & (1 << i):
                continue
            
            visited |= 1 << i
            temp += str(i)
            p = self.permutation(n, k, length + 1, visited, temp)
            if p:
                return p

            temp = temp[:-1]
            visited &= ~(1 << i)