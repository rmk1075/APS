class Solution:
    def grayCode(self, n: int) -> List[int]:
        result = [0]
        for i in range(0, n):
            temp = []
            for r in reversed(result):
                temp.append(r | (1 << i))
            result += temp
        return result