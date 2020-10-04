import java.util.Arrays;

class Solution {
    final int INF = Integer.MAX_VALUE;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        boolean[][] nodes = new boolean[n][n];
        for(int i = 0; i < n; i++) nodes[i][i] = true;
        for(int[] edge : edge_list) {
            int a = edge[0] - 1, b = edge[1] - 1;
            nodes[a][b] = nodes[b][a] = true;
        }

        int[][] dp = new int[k][n];
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < n; j++) dp[i][j] = INF;
        }
        dp[0][gps_log[0] - 1] = 0;

        for(int i = 1; i < k - 1; i++) {
            for(int j = 0; j < n; j++) {
                int temp = (j == gps_log[i] - 1 ? 0 : 1);
                for(int h = 0; h < n; h++) {
                    if(dp[i - 1][h] == INF || !nodes[h][j]) continue;

                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][h] + temp);
                }
            }
        }

        answer = INF;
        int end = gps_log[k - 1] - 1;
        for(int i = 0; i < n; i++) {
            if(!nodes[i][end]) continue;
            answer = Math.min(answer, dp[k - 2][i]);
        }

        return answer == INF ? -1 : answer;
    }
}