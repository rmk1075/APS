import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    public int numIslands(char[][] grid) {
        int M = grid.length, N = grid[0].length;
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(grid[i][j] != '1') continue;
                count++;
                grid[i][j] = '2';
                queue.offer(new int[]{i, j});
                while(!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int x = current[0];
                    int y = current[1];
                    for(int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if(nx < 0 || ny < 0 || nx == M || ny == N || grid[nx][ny] != '1') continue;
                        grid[nx][ny] = '2';
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return count;
    }
}