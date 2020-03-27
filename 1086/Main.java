import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K, len[], vals[];
    static long dp[][];
    static char nums[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new char[N][];
        len = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = br.readLine().toCharArray();
            len[i] = nums[i].length;
        }
        K = Integer.parseInt(br.readLine());

        vals = new int[N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < len[i]; j++) {
                vals[i] = ((nums[i][j] - '0') + (vals[i]*10)) % K;
            }
        }
        
        int[] digit = new int[51];
        digit[0] = 1;
        for(int i = 1; i <= 50; i++) digit[i] = (digit[i-1] * 10) % K;
        
        dp = new long[1 << N][K]; // {visited, remainder}
        dp[0][0] = 1;
        for(int i = 0; i < (1 << N); i++) {
            for(int j = 0; j < K; j++) {
                for(int k = 0; k < N; k++) {
                    if((i & (1 << k)) != 0) continue;
                    dp[i | (1 << k)][(vals[k] + ((j * digit[len[k]]) % K)) % K] += dp[i][j];
                }
            }
        }

        long a = dp[(1 << N) - 1][0], b = 1;
        if(a == 0) {
            System.out.println("0/1");
            return ;
        }

        for(int i = 1; i <= N; i++) b *= i;

        long GCD = gcd(a, b);
        System.out.println(a/GCD + "/" + b/GCD);
    }

    public static long gcd(long a, long b) {
        if(a == b || b == 0) return a;
        return gcd(b , a%b);
    }
}