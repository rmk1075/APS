import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new int[N][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for(int i = 1; i < N; i++) {
            for(int j = i; -1 < j; j--) {
                if(nums[j] < nums[i]) {
                    if(dp[i][0] < dp[j][0] + 1) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = j;
                    }
                }
            }
        }
        
        int idx = 0, val = 0;
        for(int i = 0; i < N; i++) {
            if(val < dp[i][0]) {
                idx = i;
                val = dp[i][0];
            }
        }

        System.out.println(val);

        int[] arr = new int[val];
        for(int i = val-1; -1 < i; i--) {
            arr[i] = nums[idx];
            idx = dp[idx][1];
        }
        
        for(int i = 0; i < val; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}