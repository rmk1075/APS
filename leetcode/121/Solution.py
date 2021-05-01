class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        minPrice = prices[0]
        result = 0
        for price in prices[1:]:
            if price < minPrice:
                minPrice = price
            else:
                result = max(result, price - minPrice)
        return result