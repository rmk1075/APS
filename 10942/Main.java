import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int N, M;
    static int[] nums;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            sb.append(check(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1) + "\n");
        } 

        System.out.println(sb.toString());
    }

    public static int check(int S, int E) {
        if(S == E) return 1;

        if(Math.abs(S-E) == 1) {
            if(nums[S] == nums[E]) return 1;
            else return 0;
        }

        if(0 < dp[S][E]) return 1;

        if(nums[S] != nums[E]) return 0;
        else return dp[S][E] = check(S+1, E-1);
    }
}