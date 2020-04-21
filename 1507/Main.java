import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans = 0, dist[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                ans += dist[i][j];
            }
        }
        ans /= 2;

        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                if(i == j) continue;
                for(int k = 0; k < N; k++) {
                    if(i == k || k == j) continue;
                    if(dist[i][j] == dist[i][k] + dist[k][j]) {
                        ans -= dist[i][j];
                        break;
                    } else if(dist[i][k] + dist[k][j] < dist[i][j]) {
                        System.out.println(-1);
                        return ;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, dist[][], graph[][];
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         dist = new int[N][N];
//         graph = new int[N][N];
//         for(int i = 0; i < N; i++) {
//             StringTokenizer st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < N; j++) dist[i][j] = graph[i][j] = Integer.parseInt(st.nextToken());
//         }

//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < N; j++) {
//                 if(i == j) continue;
//                 for(int k = 0; k < N; k++) {
//                     if(i == k || k == j) continue;
//                     if(dist[i][j] == dist[i][k] + dist[k][j]) {
//                         graph[i][j] = graph[j][i] = -1;
//                         break;
//                     } else if(dist[i][k] + dist[k][j] < dist[i][j]) {
//                         System.out.println(-1);
//                         return ;
//                     }
//                 }
//             }
//         }

//         int ans = 0;
//         for(int i = 0; i < N; i++) {
//             for(int j = i+1; j < N; j++) {
//                 if(graph[i][j] != -1) ans += graph[i][j];
//             }
//         }
//         System.out.println(ans);
//     }
// }