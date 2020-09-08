import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;

        int[][][] dp = new int[m + 1][n + 1][2];
        dp[1][1][0] = dp[1][1][1] = 1;

        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                switch (cityMap[i - 1][j - 1]) {
                    case 0:
                        dp[i][j][0] += (dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD;
                        dp[i][j][1] += (dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD;
                        break;
                    case 2:
                        dp[i][j][0] = dp[i - 1][j][0];
                        dp[i][j][1] = dp[i][j - 1][1];
                        break;
                }
            }
        }
        
        answer = dp[m][n][0];
        return answer;
    }
}