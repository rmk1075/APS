import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        dp = new int[N][2];
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        
        dp[0][0] = dp[0][1] = arr[0];
        for(int i = 1; i < N; i++) {
            
            if(dp[i-1][0]+arr[i] <= dp[i-1][1]) {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = dp[i-1][1] + arr[i];
            } else {
                dp[i][0] = dp[i-1][0] + arr[i];
                dp[i][1] = dp[i-1][1] + arr[i];
            }

            if(dp[i-1][0] < 0) dp[i][0] = arr[i];
            if(dp[i-1][1] < 0) dp[i][1] = arr[i];
        }

        int maxVal = Integer.MIN_VALUE;
        for(int i = 0; i < dp.length; i++) {
            maxVal = Math.max(maxVal, dp[i][0]);
            maxVal = Math.max(maxVal, dp[i][1]);
        }

        System.out.println(maxVal);
    }
}