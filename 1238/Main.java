import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, INF = Integer.MAX_VALUE, nodes[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        nodes = new int[N+1][N+1];
        for(int i = 0; i <= N; i++) Arrays.fill(nodes[i], INF);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j || nodes[i][j] == INF) continue;

                for(int k = 1; k <= N; k++) {
                    if(nodes[k][i] == INF || nodes[k][j] <= nodes[k][i] + nodes[i][j]) continue;
                    nodes[k][j] = nodes[k][i] + nodes[i][j];
                }
            }
        }

        int ans = 0;
        for(int i = 1; i <= N; i++) {
            if(i == X) continue;
            ans = Math.max(ans, nodes[X][i] + nodes[i][X]);
        }
        System.out.println(ans);
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, M, X, INF = Integer.MAX_VALUE, nodes[][];
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         X = Integer.parseInt(st.nextToken());
//         nodes = new int[N+1][N+1];
//         for(int i = 0; i <= N; i++) Arrays.fill(nodes[i], INF);
//         for(int i = 0; i < M; i++) {
//             st = new StringTokenizer(br.readLine());
//             nodes[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
//         }

//         for(int i = 1; i <= N; i++) {
//             for(int j = 1; j <= N; j++) {
//                 if(i == j || nodes[i][j] == INF) continue;

//                 for(int k = 1; k <= N; k++) {
//                     if(nodes[k][i] == INF || nodes[k][j] <= nodes[k][i] + nodes[i][j]) continue;
//                     nodes[k][j] = nodes[k][i] + nodes[i][j];
//                 }
//             }
//         }

//         int ans = 0;
//         for(int i = 1; i <= N; i++) {
//             if(i == X) continue;
//             ans = Math.max(ans, nodes[X][i] + nodes[i][X]);
//         }
//         System.out.println(ans);
//     }
// }