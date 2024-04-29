import java.util.Arrays;

class Solution {
  private static final int INF = Integer.MAX_VALUE;
  private int[] dp;
  public int coinChange(int[] coins, int amount) {
    init(amount);
    for (int current = 0; current < amount; current++) {
      if (dp[current] == INF) {
        continue;
      }

      for (int coin : coins) {
        long next = (long)current + (long)coin;
        if (amount < next) {
          continue;
        }

        dp[(int)next] = Math.min(dp[(int)next], dp[current] + 1);
      }
    }

    return dp[amount] == INF ? -1 : dp[amount] ;
  }

  private void init(int amount) {
    dp = new int[amount + 1];
    Arrays.fill(dp, INF);
    dp[0] = 0;
  }
}