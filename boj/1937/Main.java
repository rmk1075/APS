import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, map[][], dp[][];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int maxCount = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) maxCount = Math.max(maxCount, dfs(i, j));
        }
        System.out.println(maxCount);
    }

    public static int dfs(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;
        for(int d = 0; d < 4; d++) {
            int x_ = x + dx[d], y_ = y + dy[d];
            if(x_ < 0 || y_ < 0 || x_ == n || y_ == n || map[x_][y_] <= map[x][y]) continue;
            dp[x][y] = Math.max(dp[x][y], dfs(x_, y_) + 1);
        }

        return dp[x][y];
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;

// public class Main {
//     static int N;
//     static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
//     static int[][] map, dp;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         map = new int[N][N];
//         dp = new int[N][N];

//         for(int i = 0; i < N; i++) {
//             StringTokenizer st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < N; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         int maxDistance = 0;
//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < N; j++) maxDistance = Math.max(maxDistance, dfs(i, j));
//         }

//         System.out.println(maxDistance);
//     }

//     public static int dfs(int x, int y) {
//         if(dp[x][y] != 0) return dp[x][y];

//         dp[x][y] = 1;
//         for(int d = 0; d < 4; d++) {
//             int x_ = x + dx[d], y_ = y + dy[d];
//             if(x_ < 0 || y_ < 0 || x_ == N || y_ == N || map[x_][y_] <= map[x][y]) continue;

//             dp[x][y] = Math.max(dp[x][y], dfs(x_, y_) + 1);
//         }

//         return dp[x][y];
//     }
// }