public class Main {
    public static void main(String[] args) {
        
    }
}

class Solution {
    private static final int MOD = 1000000007;

    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = initDp(high);
        for (int len = 0; len < high; len++) {
            if (dp[len] == 0) {
                continue;
            }
            int count = dp[len];

            // zero
            int next = len + zero;
            if (next <= high) {
                dp[next] = (dp[next] + count) % MOD;
            }

            // one
            next = len + one;
            if (next <= high) {
                dp[next] = (dp[len + one] + count) % MOD;
            }
        }

        return sum(dp, low, high);
    }

    private int sum(int[] dp, int low, int high) {
        int result = 0;
        for (int i = low; i <= high; i++) {
            result = (result + dp[i]) % MOD;
        }
        return result % MOD;
    }

    private int[] initDp(int high) {
        int[] dp = new int[high + 1];
        dp[0] = 1;
        return dp;
    }
}
