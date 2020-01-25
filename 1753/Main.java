import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int cost;
    int idx;

    Node(int cost, int idx) {
        this.cost = cost;
        this.idx = idx;
    }

    @Override
    public int compareTo(Node o) {
        if (this.cost < o.cost)
            return -1;
        else if (this.cost == o.cost)
            return 0;
        else
            return 1;
    }
}

class Edge {
    int v2;
    int weight;

    Edge(int v2, int weight) {
        this.v2 = v2;
        this.weight = weight;
    }
}

public class Main {

    static ArrayList<Edge>[] adj;
    static int[] cost;
    static final int INF = Integer.MAX_VALUE;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        adj = new ArrayList[V + 1];
        cost = new int[V + 1];
        visited = new int[V + 1];
        Arrays.fill(cost, INF);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (adj[u] == null)
                adj[u] = new ArrayList<>();
            if (adj[v] == null)
                adj[v] = new ArrayList<>();
            adj[u].add(new Edge(v, w));
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i <= V; i++) {
            if (cost[i] != INF)
                sb.append(cost[i] + "\n");
            else
                sb.append("INF\n");
        }
        System.out.println(sb);
    }

    static void dijkstra(int K) {

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, K));
        cost[K] = INF;

        while (!q.isEmpty()) {

            Node node = q.poll();
            int now = node.idx;

            if (visited[now] == 0) visited[now] = 1;
            else continue;

            if (node.cost < cost[now]) cost[now] = node.cost;
            else continue;

            for (int i = 0; i < adj[now].size(); i++) {
                q.offer(new Node(cost[now] + adj[now].get(i).weight, adj[now].get(i).v2));
            }
        }
    }
}

// https://sunpil.tistory.com/132

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// // import java.util.StringTokenizer;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.PriorityQueue;

// class Edge {
//     int y, w;
    
//     Edge(int y, int w) {
//         this.y = y;
//         this.w = w;
//     }
// }

// public class Main {
//     static int V, E, K;
//     static int[] updated;
//     static ArrayList<Edge>[] edges;
//     static PriorityQueue<Integer> queue;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // StringTokenizer st = new StringTokenizer(br.readLine());

//         // V = Integer.parseInt(st.nextToken());
//         // E = Integer.parseInt(st.nextToken());
//         // K = Integer.parseInt(br.readLine());
        
//         String[] str = br.readLine().split(" ");
//         V = Integer.parseInt(str[0]);
//         E = Integer.parseInt(str[1]);
//         K = Integer.parseInt(br.readLine());

//         edges = new ArrayList[V+1];
//         updated = new int[V+1];
//         for(int i = 0; i < V+1; i++) {
//             edges[i] = new ArrayList<Edge>();
//             updated[i] = Integer.MAX_VALUE;
//         }
//         updated[K] = 0;

//         for(int i = 0; i < E; i++) {
//             // st = new StringTokenizer(br.readLine());
//             // edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
//             str = br.readLine().split(" ");
//             edges[Integer.parseInt(str[0])].add(new Edge(Integer.parseInt(str[1]), Integer.parseInt(str[2])));
//         }

//         queue = new PriorityQueue<Integer>();
//         queue.add(K);
//         while(!queue.isEmpty()) {
//             int current = queue.poll();
//             for(int i = 0; i < edges[current].size(); i++) {
//                 int candidate = edges[current].get(i).y;
//                 int weight = edges[current].get(i).w;

//                 if(updated[current] + weight < updated[candidate]) {
//                     updated[candidate] = updated[current] + weight;
//                     queue.add(candidate);
//                 }
//             }
//         }

//         for(int i = 1; i < V+1; i++) {
//             if(updated[i] == Integer.MAX_VALUE) System.out.println("INF");
//             else System.out.println(updated[i]);
//         }   
//     }
// }