import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int N, M;
    static int[] A, C;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        C = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][10001];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int result = Integer.MAX_VALUE;
        dp[0][C[0]] = A[0];
        if(M <= A[0]) result = C[0];
        for(int i = 1; i < N; i++) {
            dp[i][C[i]] = A[i];
            if(M <= A[i]) result = Math.min(result, C[i]);
            for(int j = 0; j < 10001; j++) {
                if(dp[i - 1][j] == -1) continue;
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                dp[i][j + C[i]] = Math.max(dp[i][j + C[i]], dp[i - 1][j] + A[i]);
                if(M <= dp[i][j + C[i]]) {
                    result = Math.min(result, j + C[i]);
                }
            }
        }
        System.out.println(result);
    }
}

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;
// import java.io.IOException;

// public class Main {
// static int N, M, maxCost;
// static int[] A, C;
// static long[] dp;
// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// StringTokenizer st = new StringTokenizer(br.readLine());

// N = Integer.parseInt(st.nextToken());
// M = Integer.parseInt(st.nextToken());

// A = new int[N];
// C = new int[N];
// st = new StringTokenizer(br.readLine());
// for(int i = 0; i < N; i++) {
// A[i] = Integer.parseInt(st.nextToken());
// }

// st = new StringTokenizer(br.readLine());
// for(int i = 0; i < N; i++) {
// C[i] = Integer.parseInt(st.nextToken());
// maxCost += C[i];
// }

// dp = new long[maxCost+1];
// for(int i = 0; i < N; i++) {
// for(int j = maxCost; C[i] <= j; j--) {
// dp[j] = Math.max(dp[j], dp[j - C[i]] + A[i]);
// }
// }

// for(int i = 0; i < maxCost; i++) {
// if(M <= dp[i]) {
// System.out.println(i);
// break;
// }
// }

// }
// }
