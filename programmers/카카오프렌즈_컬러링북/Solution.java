import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j] || picture[i][j] == 0) continue;
                answer[0]++;

                // bfs
                int cnt = 1, num = picture[i][j];
                visited[i][j] = true;
                queue.offer(new int[]{i, j});

                while(!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int cx = current[0], cy = current[1];
                    for(int d = 0; d < 4; d++) {
                        int x = cx + dx[d], y = cy + dy[d];
                        if(x < 0 || y < 0 || x == m || y == n || visited[x][y] || picture[x][y] != num) continue;

                        visited[x][y] = true;
                        cnt++;
                        queue.offer(new int[]{x, y});
                    }
                }

                answer[1] = Math.max(answer[1], cnt);
            }
        }


        return answer;
    }
}