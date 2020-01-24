import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, K;
    static int[] files, sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (0 < T--) {
            K = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            dp = new int[K + 2][K + 2];
            files = new int[K + 1];
            sum = new int[K + 1];
            for (int i = 1; i < K + 1; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + files[i];
            }

            for (int i = 2; i < K + 1; i++) {
                for (int j = i - 1; 0 < j; j--) {
                    dp[j][i] = Integer.MAX_VALUE;
                    for (int k = j; k <= i; k++) {
                        dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k + 1][i]);
                    }
                    dp[j][i] += sum[i] - sum[j - 1];
                }
            }

            System.out.println(dp[1][K]);
        }
    }
}

// import java.util.Scanner;;

// public class Main {
//     static int T, K;
//     static int[] files, sum;
//     static int[][] dp;

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         T = sc.nextInt();

//         while (0 < T--) {
//             K = sc.nextInt();

//             dp = new int[K+2][K+2]; // [K][K]
//             // for (int i = 0; i < K; i++) {
//             //     for (int j = i + 1; j < K; j++) {
//             //         dp[i][j] = Integer.MAX_VALUE;
//             //     }
//             // }

//             files = new int[K+1];
//             sum = new int[K+1];
//             for (int i = 1; i < K+1; i++) {
//                 files[i] = sc.nextInt();
//                 sum[i] = sum[i-1]+files[i];
//             }

//             for (int i = 2; i < K+1; i++) {
//                 for (int j = i - 1; 0 < j; j--) {
//                     dp[j][i] = Integer.MAX_VALUE;
//                     for (int k = j; k <= i; k++) {
//                         dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k + 1][i]);
//                     }
//                     // dp[j][i] += cal(j, i);
//                     dp[j][i] += sum[i] - sum[j-1];
//                 }
//             }

//             System.out.println(dp[1][K]);
//         }

//         sc.close();
//     }

//     // public static int cal(int x, int y) {
//     //     int val = 0;
//     //     for (int i = x; i < y + 1; i++) {
//     //         val += files[i];
//     //     }
//     //     return val;
//     // }
// }

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// public class Main {
//     static int T, K;
//     static int[] files;
//     static int[][] dp;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         T = Integer.parseInt(br.readLine());

//         while(0 < T--) {
//             K = Integer.parseInt(br.readLine());

//             dp = new int[K][K];
//             for(int i = 0; i < K; i++) {
//                 for(int j = i+1; j < K; j++) {
//                     dp[i][j] = Integer.MAX_VALUE;
//                 }
//             }

//             files = new int[K];
//             String[] str = br.readLine().split(" ");
//             for(int i = 0; i < K; i++) files[i] = Integer.parseInt(str[i]);

//             for(int i = 0; i < K; i++) {
//                 for(int j = i-1; -1 < j; j--) {
//                     for(int k = j; k < i; k++) {
//                         dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k+1][i]);
//                     }
//                     dp[j][i] += cal(j, i);
//                 }
//             }

//             System.out.println(dp[0][K-1]);
//         }
//     }

//     public static int cal(int x, int y) {
//         int val = 0;
//         for(int i = x; i < y+1; i++) {
//             val += files[i];
//         }
//         return val;
//     }
// }