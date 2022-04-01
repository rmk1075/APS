import java.util.Arrays;

class Solution {
    int[] dx = {-1, -1, 1, 1};
    int[] dy = {1, -1, 1, -1};
    int[][] visited;
    public int solution(int n) {
        visited = new int[n][n];
        int answer = dfs(0, n);
        return answer;
    }

    public int dfs(int row, int n) {
        if(row == n) return 1;
        int result = 0;
        for(int col = 0; col < n; col++) {
            if(visited[row][col] != 0) continue;
            visit(n, row, col, row + 1);
            result += dfs(row + 1, n);
            visit(n, row, col, -(row + 1));
        }
        return result;
    }

    public void visit(int n, int row, int col, int offset) {
        for(int i = 0; i < n; i++) {
            visited[row][i] += offset;
            visited[i][col] += offset;
        }

        for(int d = 0; d < 4; d++) {
            int x = row, y = col;
            while(-1 < x && -1 < y && x < n && y < n) {
                visited[x][y] += offset;
                x += dx[d];
                y += dy[d];
            }
        }
        visited[row][col] = 0;
    }
}