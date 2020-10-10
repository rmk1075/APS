import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dx = {0, 1, 1}, dy = {1, 1, 0};
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++) {
            int j = 0;
            for(char ch : br.readLine().toCharArray()) 
                map[i][j++] = ch - '0';
        }

        int maxCount = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) continue;
                maxCount = Math.max(maxCount, cal(i, j));
            }
        }

        System.out.println(maxCount * maxCount);
    }

    public static int cal(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y];

        dp[x][y] = Integer.MAX_VALUE;
        int x_, y_;
        for(int d = 0; d < 3; d ++) {
            x_ = x + dx[d];
            y_ = y + dy[d];
            if(x_ == N || y_ == M || map[x_][y_] == 0) {
                dp[x][y] = 1;
                break;
            }

            dp[x][y] = Math.min(dp[x][y], cal(x_, y_) + 1);
        }

        dp[x][y] = ((dp[x][y] == Integer.MAX_VALUE) ? 1 : dp[x][y]);
        return dp[x][y];
    }
}