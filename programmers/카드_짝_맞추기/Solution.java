import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int[] a;
    int[] b;

    Pair() {}
}

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    Pair[] pairs = new Pair[7];
    boolean[] exists = new boolean[7];
    Queue<int[]> queue = new LinkedList<>();
    public int solution(int[][] board, int r, int c) {
        int answer = 0;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] == 0) continue;
                int idx = board[i][j];
                if(pairs[idx] == null) {
                    exists[idx] = true;
                    pairs[idx] = new Pair();
                    pairs[idx].a = new int[]{i, j};
                } else pairs[idx].b = new int[]{i, j};
            }
        }

        answer = Integer.MAX_VALUE;
        for(int i = 1; i < 7; i++) {
            if(!exists[i]) continue;
            exists[i] = false;
            int[] a = pairs[i].a;
            int[] b = pairs[i].b;
            int t = dfs(board, i, r, c, a[0], a[1], b[0], b[1]);
            int s = dfs(board, i, r, c, b[0], b[1], a[0], a[1]);
            answer = Math.min(answer, Math.min(t, s));

            exists[i] = true;
        }

        return answer;
    }

    public int dfs(int[][] board, int index, int r1, int c1, int r2, int c2, int r3, int c3) {
        int result = 0;
        int t = findDistance(board, r1, c1, r2, c2);
        board[r2][c2] = 0;
        int s = findDistance(board, r2, c2, r3, c3);
        board[r3][c3] = 0;
        result = t + s + 2;

        int temp = Integer.MAX_VALUE;
        for(int i = 1; i < 7; i++) {
            if(!exists[i]) continue;
            exists[i] = false;
            int[] a = pairs[i].a;
            int[] b = pairs[i].b;
            temp = Math.min(temp, Math.min(dfs(board, i, r3, c3, a[0], a[1], b[0], b[1]), dfs(board, i, r3, c3, b[0], b[1], a[0], a[1])));
            exists[i] = true;
        }

        board[r2][c2] = board[r3][c3] = index;
        result += temp == Integer.MAX_VALUE ? 0 : temp;
        return result;
    }

    public int findDistance(int[][] board, int r1, int c1, int r2, int c2) {
        if(r1 == r2 && c1 == c2) return 0;
        
        int[][] visited = new int[4][4];
        for(int i = 0; i < 4; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        visited[r1][c1] = 0;
        queue.clear();
        queue.offer(new int[]{r1, c1});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int cnt = visited[current[0]][current[1]];
            for(int d = 0; d < 4; d++) {
                int x = current[0] + dx[d];
                int y = current[1] + dy[d];
                if(x < 0 || y < 0 || x == 4 || y == 4 || visited[x][y] <= cnt + 1) continue;
                visited[x][y] = cnt + 1;
                if(x == r2 && y == c2) continue;
                queue.offer(new int[]{x, y});
            }

            for(int d = 0; d < 4; d++) {
                int x = current[0] + dx[d];
                int y = current[1] + dy[d];
                while(-1 < x && -1 < y && x < 4 && y < 4 && board[x][y] == 0) {
                    x += dx[d];
                    y += dy[d];
                }
                if(x == -1 || x == 4) x -= dx[d];
                if(y == -1 || y == 4) y -= dy[d];
                if(x < 0 || y < 0 || x == 4 || y == 4 || visited[x][y] <= cnt + 1) continue;
                visited[x][y] = cnt + 1;
                if(x == r2 && y == c2) continue;
                queue.offer(new int[]{x, y});
            }
        }

        int distance = visited[r2][c2];
        return distance;
    }
}