import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] dp;
    static char[] A, B;
    static String lcs[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        int a = A.length + 1, b = B.length + 1;
        lcs = new String[a][b];
        dp = new int[a][b];

        for(int i = 0; i < a; i++) lcs[i][0] = "";
        for(int i = 0; i < b; i++) lcs[0][i] = "";

        for(int i = 1; i < a; i++) {
            for(int j = 1; j < b; j++) {
                if(A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    lcs[i][j] = lcs[i-1][j-1] + String.valueOf(A[i-1]);
                } else {
                    if(dp[i-1][j] < dp[i][j-1]) {
                        dp[i][j] = dp[i][j-1];
                        lcs[i][j] = lcs[i][j-1];
                    } else {
                        dp[i][j] = dp[i-1][j];
                        lcs[i][j] = lcs[i-1][j];
                    }
                }
            }
        }

        System.out.println(dp[a - 1][b - 1] + "\n" + lcs[a - 1][b - 1]);
    }    
}