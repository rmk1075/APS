import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int MOD = 1000000007;
        int N = Integer.parseInt(br.readLine());
        long arr[][] = new long[1000001][2];
        arr[0][0] = 1;
        arr[1][0] = 2;
        arr[1][1] = 1;
        for(int i = 2; i <= N; i++) {
            arr[i][1] = ((arr[i-1][0] + arr[i-2][0]) % MOD + arr[i-2][1]) % MOD;
            arr[i][0] = (arr[i-2][0] + 2*arr[i][1]) % MOD;
        }
        System.out.println(arr[N][0]);
    }
}