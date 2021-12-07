class Solution {
    public int maxProfit(int[] prices) {
        int[] profits = {0, -prices[0], 0};
        for(int price : prices) {
            int prev = profits[0];
            profits[0] = profits[1] + price;
            profits[1] = Math.max(profits[1], profits[2] - price);
            profits[2] = Math.max(profits[2], prev);
        }
        return Math.max(profits[0], profits[2]);
    }
}