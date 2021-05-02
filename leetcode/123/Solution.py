class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        first_price, second_price, first_profit, second_profit = 100001, 100001, 0, 0
        for price in prices:
            first_price = min(first_price, price)
            first_profit = max(first_profit, price - first_price)
            second_price = min(second_price, price - first_profit)
            second_profit = max(second_profit, price - second_price)
        return second_profit