class Solution:
    def mySqrt(self, x: int) -> int:
        if x in (0, 1):
            return x
        left, right = 0, x
        while left < right:
            mid = (left + right) // 2
            if x == mid**2:
                return mid
            elif x < mid**2:
                right = mid
            else:
                left = mid + 1
        return right - 1
