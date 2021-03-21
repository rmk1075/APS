class Solution(object):
    def combine(self, n, k):
        result = list()
        for i in range(1, n - k + 2):
            self.find(n, k, [i], i, 1, result)
        return result

    def find(self, n, k, comb, index, cnt, result):
        if cnt == k:
            result.append(comb[:])
            return
        
        for i in range(index + 1, n + 1):
            self.find(n, k, comb + [i], i, cnt + 1, result)