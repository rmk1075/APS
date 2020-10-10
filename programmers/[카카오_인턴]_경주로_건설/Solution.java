import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    final int INF = Integer.MAX_VALUE;
    int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    public int solution(int[][] board) {
        int answer = 0;

        int N = board.length;
        int[][] cost = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(cost[i], INF);
        cost[0][0] = 100;

        Queue<int[]> queue = new LinkedList<>();    // {x, y, d, cost}
        queue.offer(new int[]{0, 0, 2, 0});
        queue.offer(new int[]{0, 0, 3, 0});
        int x, y, c;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int d = 0; d < 4; d++) {
                if(Math.abs(current[2] - d) == 2) continue;
                
                x = current[0] + dx[d];
                y = current[1] + dy[d];
                c = (d == current[2]) ? current[3] + 100 : current[3] + 600;

                if(x < 0 || y < 0 || x == N || y == N || board[x][y] == 1 || cost[x][y] < c) continue;
                cost[x][y] = c;
                queue.offer(new int[]{x, y, d, c});
            }
        }

        answer = cost[N - 1][N - 1];
        return answer;
    }
}