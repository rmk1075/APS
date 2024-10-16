// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.PriorityQueue;
// import java.util.StringTokenizer;

// public class Main {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int N = Integer.parseInt(st.nextToken());
//         int M = Integer.parseInt(st.nextToken());
//         int[][] locations = new int[N][2];
//         for (int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             locations[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
//         }

//         boolean[][] connected = new boolean[N][N];
//         for (int j = 0; j < M; j++) {
//             st = new StringTokenizer(br.readLine());
//             int u = Integer.parseInt(st.nextToken()) - 1;
//             int v = Integer.parseInt(st.nextToken()) - 1;
//             connected[u][v] = true;
//             connected[v][u] = true;
//         }

//         double[][] distances = new double[N][N];
//         for (int i = 0; i < N; i++) {
//             for (int j = i + 1; j < N; j++) {
//                 if (connected[i][j]) {
//                     distances[i][j] = 0;
//                     distances[j][i] = 0;
//                 } else {
//                     double distance = calculate(locations[i][0], locations[j][0], locations[i][1], locations[j][1]);
//                     distances[i][j] = distances[j][i] = distance;
//                 }
//             }
//         }

//         PriorityQueue<double[]> pq = new PriorityQueue<>(
//                 (o1, o2) -> Double.compare(o1[1], o2[1]));
//         pq.offer(new double[] { 0, 0 });
//         double sum = 0;
//         boolean[] visited = new boolean[N];
//         while (!pq.isEmpty()) {
//             double[] node = pq.poll();
//             int index = (int) node[0];
//             if (visited[index]) {
//                 continue;
//             }
//             visited[index] = true;
//             sum += node[1];
//             for (int i = 0; i < N; i++) {
//                 if (i == index) {
//                     continue;
//                 }
//                 pq.offer(new double[]{i, distances[index][i]});
//             }
//         }
//         System.out.println(String.format("%.2f", sum));
//     }

//     private static double calculate(int a, int b, int c, int d) {
//         return Math.sqrt(Math.pow(a - b, 2) + Math.pow(c - d, 2));
//     }
// }

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] locations = new int[N][2];
        int[] group = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            locations[i] = new int[] { Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()) };
            group[i] = i;
        }

        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            union(group, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1);
        }

        PriorityQueue<double[]> pq = new PriorityQueue<>(
                (o1, o2) -> Double.compare(o1[2], o2[2]));
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (find(group, i) == find(group, j)) {
                    continue;
                }
                pq.offer(new double[] { i, j,
                        calculate(locations[i][0], locations[j][0], locations[i][1], locations[j][1])
                });
            }
        }

        double sum = 0;
        while (!pq.isEmpty()) {
            double[] edge = pq.poll();
            int u = (int) edge[0];
            int v = (int) edge[1];
            double w = edge[2];
            if (find(group, u) == find(group, v)) {
                continue;
            }
            union(group, u, v);
            sum += w;
        }
        System.out.println(String.format("%.2f", sum));
    }

    private static double calculate(int a, int b, int c, int d) {
        return Math.sqrt(Math.pow(a - b, 2) + Math.pow(c - d, 2));
    }

    private static int find(int[] group, int index) {
        if (group[index] == index) {
            return index;
        }
        int root = find(group, group[index]);
        group[index] = root;
        return root;
    }

    private static void union(int[] group, int a, int b) {
        int aa = find(group, a);
        int bb = find(group, b);
        group[aa] = group[bb] = Math.min(aa, bb);
    }
}