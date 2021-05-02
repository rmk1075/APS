class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        result = 0
        p = 10001
        for i in range(0, len(prices) - 1):
            if prices[i] <= prices[i + 1]:
                if prices[i] < p:
                    p = prices[i]
            else:
                if p != 10001:
                    result += prices[i] - p
                    p = 10001
            
        if p < prices[-1]:
            result += prices[-1] - p

        return result