import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int dest, w;

    Edge(int dest, int w) {
        this.dest = dest;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}

public class Main {
    static int N, M, X, INF = Integer.MAX_VALUE;
    static ArrayList<Edge>[] edges, edgesReverse;
    static Edge[] nodes, nodesReverse;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        edges = new ArrayList[N+1];
        edgesReverse = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
            edgesReverse[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            edges[u].add(new Edge(v, w));
            edgesReverse[v].add(new Edge(u, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        nodes = new Edge[N+1];
        checked = new boolean[N+1];
        for(int i = 1; i <= N; i++) {
            if(i == X) nodes[i] = new Edge(i, 0);
            else nodes[i] = new Edge(i, INF);
            pq.offer(nodes[i]);
        }
        
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            for(Edge next : edges[edge.dest]) {
                if(checked[next.dest] || nodes[next.dest].w <= nodes[edge.dest].w + next.w) continue;
                pq.remove(nodes[next.dest]);
                nodes[next.dest].w = nodes[edge.dest].w + next.w;
                pq.offer(nodes[next.dest]);
            }
            checked[edge.dest] = true;
        }

        nodesReverse = new Edge[N+1];
        for(int i = 1; i <= N; i++) checked[i] = false;
        for(int i = 1; i <= N; i++) {
            if(i == X) nodesReverse[i] = new Edge(i, 0);
            else nodesReverse[i] = new Edge(i, INF);
            pq.offer(nodesReverse[i]);
        }
        
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            for(Edge next : edgesReverse[edge.dest]) {
                if(checked[next.dest] || nodesReverse[next.dest].w <= nodesReverse[edge.dest].w + next.w) continue;
                pq.remove(nodesReverse[next.dest]);
                nodesReverse[next.dest].w = nodesReverse[edge.dest].w + next.w;
                pq.offer(nodesReverse[next.dest]);
            }
            checked[edge.dest] = true;
        }

        int ans = 0;
        for(int i = 1; i <= N; i++) ans = Math.max(ans, nodes[i].w + nodesReverse[i].w);

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