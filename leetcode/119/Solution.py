class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        result = [1]
        for i in range(rowIndex):
            temp = [0 for _ in range(i + 2)]
            for j in range(i + 1):
                temp[j] += result[j]
                temp[j + 1] += result[j]
            result = temp[:]

        return result