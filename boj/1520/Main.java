import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int M, N;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(find(0, 0));
    }

    public static int find(int x, int y) {
        if (x == M - 1 && y == N - 1)
            return 1;

        if (dp[x][y] == -1) {
            dp[x][y] = 0;
            for (int i = 0; i < 4; i++) {
                int x_ = x + dx[i];
                int y_ = y + dy[i];
                if (x_ < 0 || M - 1 < x_ || y_ < 0 || N - 1 < y_)
                    continue;

                if (map[x_][y_] < map[x][y])
                    dp[x][y] += find(x_, y_);
            }
        }

        return dp[x][y];
    }
}