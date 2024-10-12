import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] road = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(road[i], Integer.MAX_VALUE);
            road[i][i] = 0;
        }

        int[][] next = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(next[i], -1);
            next[i][i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            if (c < road[a][b]) {
                road[a][b] = road[b][a] = c;
                next[a][b] = b;
                next[b][a] = a;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (road[i][k] == Integer.MAX_VALUE || road[k][j] == Integer.MAX_VALUE || road[i][j] <= road[i][k] + road[k][j]) {
                        continue;
                    }
                    road[i][j] = road[i][k] + road[k][j];
                    next[i][j] = next[i][k];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    sb.append("- ");
                } else {
                    sb.append((next[i][j] + 1) + " ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}


// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.PriorityQueue;
// import java.util.StringTokenizer;

// public class Main {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int n = Integer.parseInt(st.nextToken());
//         int m = Integer.parseInt(st.nextToken());

//         int[][] road = new int[n][n];
//         for (int i = 0; i < n; i++) {
//             Arrays.fill(road[i], Integer.MAX_VALUE);
//             road[i][i] = 0;
//         }

//         int[][] next = new int[n][n];
//         for (int i = 0; i < n; i++) {
//             Arrays.fill(next[i], -1);
//             next[i][i] = i;
//         }

//         for (int i = 0; i < m; i++) {
//             st = new StringTokenizer(br.readLine());
//             int a = Integer.parseInt(st.nextToken()) - 1;
//             int b = Integer.parseInt(st.nextToken()) - 1;
//             int c = Integer.parseInt(st.nextToken());
//             if (c < road[a][b]) {
//                 road[a][b] = road[b][a] = c;
//             }
//         }

//         for (int i = 0; i < n; i++) {
//             find(n, i, next, road);
//         }

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (i == j) {
//                     System.out.print("- ");
//                 } else {
//                     System.out.print((next[i][j] + 1) + " ");
//                 }
//             }
//             System.out.println();
//         }
//     }

//     private static void find(int n, int src, int[][] next, int[][] road) {
//         boolean[] visited = new boolean[n];
//         PriorityQueue<int[]> pq = new PriorityQueue<>(
//                 (o1, o2) -> Integer.compare(o1[1], o2[1]));
//         pq.offer(new int[] { src, 0 });

//         int[] distances = new int[n];
//         Arrays.fill(distances, Integer.MAX_VALUE);
//         distances[src] = 0;

//         while (!pq.isEmpty()) {
//             int[] node = pq.poll();
//             int index = node[0];
//             if (visited[index]) {
//                 continue;
//             }
//             visited[index] = true;

//             for (int i = 0; i < n; i++) {
//                 if (visited[i] || road[index][i] == Integer.MAX_VALUE
//                         || distances[i] < distances[index] + road[index][i]) {
//                     continue;
//                 }

//                 distances[i] = distances[index] + road[index][i];
//                 next[src][i] = index == src ? i : next[src][index];
//                 pq.offer(new int[] { i, distances[i] });
//             }
//         }
//     }
// }
