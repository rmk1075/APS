import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * overview
 * 
 * {0, 0} 에서 {m - 1, n - 1} 까지의 최단 거리를 찾아야 한다.
 * 최단 거리를 구하기 위해서는 두가지 요소를 확인해야 한다.
 * 1) time_map[x][y] 가 -1 인 경우. 파티 테이블이 위치한 경우.
 * 2) 대화를 하는 시간이 s 보다 작은가.
 * 
 * 이를 구하기 위해서 출발지에서 목적지까지 갈 수 있는 최단거리인 m + n - 2 ~ m * n - 1 까지를 범위로하여 탐색을 수행한다.
 * 최단거리부터 각 거리를 기준으로 탐색을 수행해서 해당 거리가 유효한 경우, 그 결과를 반환한다.
 * 
 * 
 * description
 * 
 * 출발지에서 목적지까지 갈 수 있는 경우의 수인 m + n - 2 ~ m * n - 1 을 범위로 탐색을 시작한다.
 * 각 탐색에서 방문한 경우의 대화시간을 저장할 배열을 dp 로 선언한 뒤, dp 에 들어갈 수 있는 최대값, 최대 대화 시간인 s 에 1 을 더한 값으로 초기화한다.
 * s + 1 로 초기화 하는 이유는 s 를 넘어가는 경우는 유효하지 않는 경우이기 때문에 탐색에서 비교를 하면서 미리 예외로 빼기 위함이다.
 * 탐색은 bfs 로 하여 이미 dp 에 더 작은 대화 시간으로 방문했거나 파티 테이블이 있는 경우는 방문하지 않도록 하여 탐색을 수행한다.
 * 탐색이 종료된 뒤에 목적지의 값 dp[m - 1][n - 1] 을 확인하여 s + 1 이 아닌 경우, 목적지를 방문한 것이기 때문에 탐색을 종료하고 결과를 반환한다.
 */
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