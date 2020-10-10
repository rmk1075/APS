import java.util.Scanner;

public class Main {
    static int n, k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        int[] dp = new int[k+1];
        int[] coins = new int[n+1];
        for(int i = 1; i < n+1; i++) {
            coins[i] = sc.nextInt();
        }
        sc.close();

        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = coins[i]; j <= k; j++) {
                dp[j] += dp[j-coins[i]];
            }
        }

        System.out.println(dp[k]);
    }
}