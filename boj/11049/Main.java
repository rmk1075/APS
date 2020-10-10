import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int N;
    static int[][] mat, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        mat = new int[N][2];
        dp = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mat[i][0] = Integer.parseInt(st.nextToken());
            mat[i][1] = Integer.parseInt(st.nextToken());
        }

        if(N == 1) {
            System.out.println(0);
            System.exit(0);
        }

        for(int i = 1; i < N; i++) {
            for(int j = i-1; -1 < j; j--) {
                dp[j][i] = Integer.MAX_VALUE;

                for(int k = j; k < i; k++) {
                    dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k+1][i] + mat[j][0] * mat[k+1][0] * mat[i][1]);
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}