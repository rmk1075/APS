class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1
        elif x == 0 or x == 1:
            return x

        result = self.cal(x, abs(n))
        return result if 0 <= n else 1 / result
        
    def cal(self, x: float, n: int) -> float:

        print(x, n)

        if n == 0:
            return 1
        elif n == 1:
            return x
        elif x % 2 == 0:
            return self.cal(x * x, n // 2)
        else:
            return x * self.cal(x * x, (n - 1) // 2)