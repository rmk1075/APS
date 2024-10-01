package leetcode.3286;

public class Main {
    
}

import java.util.*;

class Solution {
    private static final int[] dx = { -1, 0, 1, 0 };
    private static final int[] dy = { 0, 1, 0, -1 };

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        visited[0][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0, grid.get(0).get(0) });
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (nx < 0 || ny < 0 || nx == m || ny == n) {
                    continue;
                }

                int temp = current[2] + grid.get(nx).get(ny);
                if (health <= temp || visited[nx][ny] <= temp) {
                    continue;
                }

                visited[nx][ny] = temp;
                queue.offer(new int[] { nx, ny, temp });
            }
        }
        return visited[m - 1][n - 1] < health;
    }
}