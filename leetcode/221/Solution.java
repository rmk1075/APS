import java.util.Queue;
import java.util.LinkedList;

class Solution {
    int m, n;
    int[] dx = {0, 1, 1};
    int[] dy = {1, 0, 1};
    Queue<int[]> queue = new LinkedList<>();
    public int maximalSquare(char[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '0') continue;
                result = Math.max(result, bfs(matrix, i, j));
            }
        }
        return cal(result);
    }

    public int cal(int value) {
        int result = 0;
        for(int i = 1; i <= value; i++) result += 2 * i - 1;
        return result;
    }
    
    public int bfs(char[][] matrix, int srcX, int srcY) {
        queue.clear();
        queue.offer(new int[]{srcX, srcY});
        
        boolean[][] visited = new boolean[m][n];
        visited[srcX][srcY] = true;
        
        int count = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(0 < size--) {
                int[] current = queue.poll();
                for(int d = 0; d < 3; d++) {
                    int x = current[0] + dx[d];
                    int y = current[1] + dy[d];
                    if(x == m || y == n || matrix[x][y] == '0') return count;
                    if(visited[x][y]) continue;
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
            count++;
        }
        return count;
    }
}