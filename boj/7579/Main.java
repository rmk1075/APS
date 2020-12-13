import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int N, M, maxCost;
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
            maxCost += C[i];
        }

        dp = new int[N][10000];
        int answer = Integer.MAX_VALUE;
        for (int i = C[0]; i < 10000; i++) {
            dp[0][i] = A[0];

            if (M <= dp[0][i])
                answer = Math.min(answer, i);
        }

        for (int i = 1; i < N; i++) {
            int m = A[i], c = C[i];
            for (int j = 0; j < 10000; j++) {
                if (c <= j)
                    dp[i][j] = Math.max(dp[i - 1][j - c] + m, dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];

                if (M <= dp[i][j])
                    answer = Math.min(answer, j);
            }
        }

        System.out.println(answer);
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
