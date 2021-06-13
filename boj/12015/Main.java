import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N + 1];
        dp[0] = Integer.MAX_VALUE;
        
        int index = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int target = Integer.parseInt(st.nextToken());
            if(dp[index] < target) dp[++index] = target;
            else find(dp, index, target);
        }
        System.out.println(index + 1);
    }

    private static void find(int[] dp, int index, int target) {
        int left = 0, right = index;
        while(left < right) {
            int mid = (left + right) / 2;
            if(dp[mid] == target) return ;
            if(dp[mid] < target) left = mid + 1;
            else right = mid;
        }

        dp[left] = target;
    }
}