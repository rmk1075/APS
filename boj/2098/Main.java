import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        int result = find(0, 1);
        System.out.println(result);
    }

    private static void init() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    private static int find(int current, int visited) {
        if (visited == (1 << N) - 1) {
            dp[current][visited] = map[current][0] == 0 ? Integer.MAX_VALUE : map[current][0];
            return dp[current][visited];
        }

        if (dp[current][visited] != -1) {
            return dp[current][visited];
        }

        dp[current][visited] = Integer.MAX_VALUE;
        for (int next = 1; next < N; next++) {
            if ((visited & (1 << next)) != 0 || map[current][next] == 0) {
                continue;
            }

            int distance = find(next, visited | (1 << next));
            if (distance != Integer.MAX_VALUE) {
                dp[current][visited] = Math.min(dp[current][visited], distance + map[current][next]);
            }
        }
        return dp[current][visited];
    }
}