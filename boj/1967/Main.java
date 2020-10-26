import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] visited;
    static List<int[]>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        edges = new List[N + 1];
        for(int i = 0; i < N + 1; i++) edges[i] = new ArrayList<>();

        StringTokenizer st;
        int u, v, w;
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges[u].add(new int[]{v, w});
            edges[v].add(new int[]{u, w});
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        visited[1] = true;

        int cw, maxNode = 1, maxWeight = 0;
        int[] current;
        while(!queue.isEmpty()) {
            current = queue.poll();
            u = current[0];
            cw = current[1];

            if(maxWeight < cw) {
                maxNode = u;
                maxWeight = cw;
            }

            for(int[] edge : edges[u]) {
                v = edge[0];
                w = edge[1];

                if(visited[v]) continue;

                visited[v] = true;
                queue.offer(new int[]{v, cw + w});
            }
        }

        Arrays.fill(visited, false);
        queue.offer(new int[]{maxNode, 0});
        visited[maxNode] = true;

        maxWeight = 0;
        while(!queue.isEmpty()) {
            current = queue.poll();
            u = current[0];
            cw = current[1];

            maxWeight = Math.max(maxWeight, cw);
            for(int[] edge : edges[u]) {
                v = edge[0];
                w = edge[1];

                if(visited[v]) continue;

                visited[v] = true;
                queue.offer(new int[]{v, cw + w});
            }
        }

        System.out.println(maxWeight);
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// class Node {
//     int dest, w;

//     Node(int dest, int w) {
//         this.dest = dest;
//         this.w = w;
//     }
// }

// public class Main {
//     static int N;
//     static boolean[] visited;
//     static ArrayList<Node>[] edges;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         visited = new boolean[N+1];
//         edges = new ArrayList[N+1];
//         for(int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
//         StringTokenizer st;
//         for(int i = 0; i < N-1; i++) {
//             st = new StringTokenizer(br.readLine());
//             int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
//             edges[u].add(new Node(v, w));
//             edges[v].add(new Node(u, w));
//         }

//         int maxIdx = 0, maxVal = 0;
//         Queue<Node> queue = new LinkedList<>();
//         queue.offer(new Node(1, 0));
//         while(!queue.isEmpty()) {
//             Node current = queue.poll();
//             if(maxVal < current.w) {
//                 maxIdx = current.dest;
//                 maxVal = current.w;
//             }

//             for(Node dest : edges[current.dest]) {
//                 if(visited[dest.dest]) continue;
//                 visited[dest.dest] = true;
//                 queue.offer(new Node(dest.dest, current.w + dest.w));
//             }
//         }

//         Arrays.fill(visited, false);
//         queue.offer(new Node(maxIdx, 0));
//         visited[maxIdx] = true;
//         maxVal = 0;
//         while(!queue.isEmpty()) {
//             Node current = queue.poll();
//             maxVal = Math.max(maxVal, current.w);

//             for(Node dest : edges[current.dest]) {
//                 if(visited[dest.dest]) continue;
//                 visited[dest.dest] = true;
//                 queue.offer(new Node(dest.dest, current.w + dest.w));
//             }
//         }

//         System.out.println(maxVal);
//     }
// }