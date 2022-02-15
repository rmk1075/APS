import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[][] dp;
    public int[] solution(int m, int n, int s, int[][] time_map) {
        dp = new int[m][n];
        int moveDistance = 0;
        for(int i = m + n - 2; i < m * n; i++) {
            for(int j = 0; j < m; j++) Arrays.fill(dp[j], s + 1);
            dp[0][0] = 0;
            valid(i, s, m, n, time_map);
            if(dp[m - 1][n - 1] != s + 1) {
                moveDistance = i;
                break;
            }
        }
        return new int[]{moveDistance, dp[m - 1][n - 1]};
    }

    public void valid(int dist, int time, int m, int n, int[][] time_map) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // {x, y, time}
        while(0 < dist-- && !queue.isEmpty()) {
            int size = queue.size();
            while(0 < size--) {
                int[] current = queue.poll();
                for(int d = 0; d < 4; d++) {
                    int x = current[0] + dx[d];
                    int y = current[1] + dy[d];
                    if(x < 0 || y < 0 || x == m || y == n || time_map[x][y] == -1 || (long)dp[x][y] <= (long)current[2] + (long)time_map[x][y]) continue;
                    dp[x][y] = current[2] + time_map[x][y];

                    if(x == m - 1 && y == n - 1) continue;
                    queue.offer(new int[]{x, y, dp[x][y]});
                }
            }
        }
    }
}