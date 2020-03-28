import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, srcColor, cost[][], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int INF = 1000001, ans = INF;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) dp[0][j] = INF;
            dp[0][i] = cost[0][i];

            for(int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j-1][1], dp[j-1][0]) + cost[j][2];
            }

            dp[N-1][i] = INF;
            for(int j = 0; j < 3; j++) ans = Math.min(ans, dp[N-1][j]);
        }

        System.out.println(ans);
    }
}