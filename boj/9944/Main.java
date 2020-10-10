import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, emptyCount, minCount;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = 1;
        String input = "";
        while((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            if(!st.hasMoreTokens()) break;
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            visited = new boolean[N][M];

            emptyCount = 0;
            for(int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == '.') emptyCount++;
                }
            }

            if(emptyCount == 1) {
                sb.append("Case " + t++ + ": 0\n");
                continue;
            }

            minCount = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == '*') continue;

                    visited[i][j] = true;
                    for(int d = 0; d < 4; d++) dfs(i, j, d, 1, 1);
                    visited[i][j] = false;
                }
            }

            sb.append("Case " + t++ +  ": " + (minCount == Integer.MAX_VALUE ? -1 : minCount) + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int x, int y, int d, int cnt, int step) {        
        if(cnt == emptyCount) {
            minCount = Math.min(minCount, step);
            return ;
        }

        int x_ = x + dx[d], y_ = y + dy[d];
        // change direction
        if(x_ < 0 || y_ < 0 || x_ == N || y_ == M || map[x_][y_] == '*' || visited[x_][y_]) {
            for(int d_ = 0; d_ < 4; d_++) {
                if(d == d_) continue;
                x_ = x + dx[d_];
                y_ = y + dy[d_];
                if(x_ < 0 || y_ < 0 || x_ == N || y_ == M || map[x_][y_] == '*' || visited[x_][y_]) continue;
                visited[x_][y_] = true;
                dfs(x_, y_, d_, cnt+1, step+1);
                visited[x_][y_] = false;
            }

            return ;
        }

        visited[x_][y_] = true;
        dfs(x_, y_, d, cnt+1, step);
        visited[x_][y_] = false;
    }
}