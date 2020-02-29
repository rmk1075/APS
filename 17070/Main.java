import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board;
    static int[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        map = new int[3][N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        map[0][0][1] = 1;
        for(int i = 2; i < N; i++) {
            if(board[0][i] == 1) break;
            map[0][0][i] = map[0][0][i-1];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 2; j < N; j++) {
                if(board[i][j] == 1) continue;

                map[0][i][j] = map[0][i][j-1] + map[2][i][j-1];
                map[1][i][j] = map[1][i-1][j] + map[2][i-1][j];

                if(board[i-1][j] == 1 || board[i][j-1] == 1) continue;
                map[2][i][j] = map[0][i-1][j-1] + map[1][i-1][j-1] + map[2][i-1][j-1];
            }
        }
        System.out.println(map[0][N-1][N-1] + map[1][N-1][N-1] + map[2][N-1][N-1]);
    }
}






// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;
// import java.io.IOException;

// public class Main {
//     static int N;
//     static int[][] house;
//     static int[][][] dp;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         N = Integer.parseInt(br.readLine());
//         house = new int[N + 1][N + 1];
//         dp = new int[N + 1][N + 1][3];

//         for(int i = 1; i < N + 1; i++) {
//             StringTokenizer st = new StringTokenizer(br.readLine());
//             for(int j = 1; j < N + 1; j++) {
//                 house[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         for(int i = 2; i < N + 1; i++) {
//             if(house[1][i] == 1) break;

//             dp[1][i][0] = 1;
//         }

//         for(int i = 1; i < N + 1; i++) {
//             for(int j = 3; j < N + 1; j++) {
//                 if(house[i][j] == 1) continue;

//                 dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
//                 dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];

//                 if(house[i-1][j-1] == 0 && house[i-1][j] == 0 && house[i][j-1] == 0)
//                     dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
//             }
//         }

//         System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
//         br.close();
//     }
// }