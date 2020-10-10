import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Vertex implements Comparable<Vertex> {
    int distance, idx;

    Vertex(int distance, int idx) {
        this.distance = distance;
        this.idx = idx;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(this.distance, o.distance);
    }
}

class Edge {
    int v, w;

    Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

public class Main {
    static int V, E, K;
    static int[] distance;
    static boolean[] check;
    static ArrayList<Edge>[] edges;
    static PriorityQueue<Vertex> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        check = new boolean[V + 1];
        distance = new int[V + 1];
        for (int i = 0; i < V + 1; i++) distance[i] = Integer.MAX_VALUE;

        edges = new ArrayList[V + 1];
        for(int i = 0; i < V + 1; i++) edges[i] = new ArrayList<>();
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        queue = new PriorityQueue<Vertex>();
        queue.offer(new Vertex(0, K));
        while(!queue.isEmpty()) {
            Vertex vertex = queue.poll();

            if(check[vertex.idx] == true) continue;
            check[vertex.idx] = true;

            if(distance[vertex.idx] < vertex.distance) continue;
            distance[vertex.idx] = vertex.distance;

            for(int i = 0; i < edges[vertex.idx].size(); i++) {
                queue.offer(new Vertex(distance[vertex.idx] + edges[vertex.idx].get(i).w, edges[vertex.idx].get(i).v));
            }
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i <= V; i++) {
            if (check[i] == false) sb.append("INF\n");
            else sb.append(distance[i] + "\n");
        }
        System.out.println(sb);
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.PriorityQueue;
// import java.util.StringTokenizer;

// class Edge implements Comparable<Edge> {
//     int v, w;

//     Edge(int v, int w) {
//         this.v = v;
//         this.w = w;
//     }

//     @Override
//     public int compareTo(Edge o) {
//         return Integer.compare(this.w, o.w);
//     }
// }

// public class Main {
//     static int V, E, src, INF = Integer.MAX_VALUE;
//     static ArrayList<Edge>[] edges;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         V = Integer.parseInt(st.nextToken());
//         E = Integer.parseInt(st.nextToken());
//         src = Integer.parseInt(br.readLine());
//         Edge[] vertex = new Edge[V+1];
//         edges = new ArrayList[V+1];
//         for(int i = 1; i <= V; i++) edges[i] = new ArrayList<>();
//         for(int i = 0; i < E; i++) {
//             st = new StringTokenizer(br.readLine());
//             edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
//         }

//         PriorityQueue<Edge> pq = new PriorityQueue<>();
//         boolean[] visited = new boolean[V+1];
//         for(int i = 1; i <= V; i++) {
//             if(i == src) vertex[i] = new Edge(i, 0);
//             else vertex[i] = new Edge(i, INF);
//             pq.offer(vertex[i]);
//         }

//         int ev, cv;
//         while(!pq.isEmpty()) {
//             Edge edge = pq.poll();
//             ev = edge.v;
//             if(vertex[ev].w == INF) continue;
//             for(Edge candidate : edges[ev]) {
//                 cv = candidate.v;
//                 if(visited[cv] || vertex[cv].w <= vertex[ev].w + candidate.w) continue;
//                 vertex[cv].w = vertex[ev].w + candidate.w;
//                 pq.remove(vertex[cv]);
//                 pq.offer(vertex[cv]);
//             }
//             visited[ev] = true;
//         }

//         StringBuilder sb = new StringBuilder();
//         for(int i = 1; i <= V; i++) {
//             if(vertex[i].w == INF) sb.append("INF\n");
//             else sb.append(vertex[i].w + "\n");
//         }
//         System.out.println(sb);
//     }
// }

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.StringTokenizer;
// import java.util.PriorityQueue;

// class Vertex implements Comparable<Vertex> {
//     int distance, idx;

//     Vertex(int distance, int idx) {
//         this.distance = distance;
//         this.idx = idx;
//     }

//     @Override
//     public int compareTo(Vertex o) {
//         if (this.distance < o.distance)
//             return -1;
//         else if (this.distance == o.distance)
//             return 0;
//         else
//             return 1;
//     }
// }

// class Edge {
//     int v, w;

//     Edge(int v, int w) {
//         this.v = v;
//         this.w = w;
//     }
// }

// public class Main {
//     static int V, E, K;
//     static int[] distance;
//     static boolean[] check;
//     static ArrayList<Edge>[] edges;
//     static PriorityQueue<Vertex> queue;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         V = Integer.parseInt(st.nextToken());
//         E = Integer.parseInt(st.nextToken());
//         K = Integer.parseInt(br.readLine());
//         check = new boolean[V + 1];

//         distance = new int[V + 1];
//         for (int i = 0; i < V + 1; i++) {
//             distance[i] = Integer.MAX_VALUE;
//         }

//         edges = new ArrayList[V + 1];
//         for(int i = 0; i < V + 1; i++) {
//             edges[i] = new ArrayList<>();
//         }
//         for(int i = 0; i < E; i++) {
//             st = new StringTokenizer(br.readLine());

//             int u = Integer.parseInt(st.nextToken());
//             int v = Integer.parseInt(st.nextToken());
//             int w = Integer.parseInt(st.nextToken());

//             edges[u].add(new Edge(v, w));
//         }

//         dijkstra(K);

//         StringBuilder sb = new StringBuilder("");
//         for (int i = 1; i <= V; i++) {
//             if (check[i] == false) sb.append("INF\n");
//             else sb.append(distance[i] + "\n");
//         }

//         System.out.println(sb);
//     }

//     public static void dijkstra(int idx) {
//         queue = new PriorityQueue<Vertex>();
//         queue.offer(new Vertex(0, K));

//         while(!queue.isEmpty()) {
//             Vertex vertex = queue.poll();

//             if(check[vertex.idx] == true) continue;
//             check[vertex.idx] = true;

//             if(distance[vertex.idx] < vertex.distance) continue;
//             distance[vertex.idx] = vertex.distance;

//             for(int i = 0; i < edges[vertex.idx].size(); i++) {
//                 queue.offer(new Vertex(distance[vertex.idx] + edges[vertex.idx].get(i).w, edges[vertex.idx].get(i).v));
//             }
//         }
//     }
// }

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.StringTokenizer;
// import java.util.PriorityQueue;

// class Vertex implements Comparable<Vertex> {
//     int distance, idx;

//     Vertex(int distance, int idx) {
//         this.distance = distance;
//         this.idx = idx;
//     }

//     @Override
//     public int compareTo(Vertex o) {
//         if (this.distance < o.distance)
//             return -1;
//         else if (this.distance == o.distance)
//             return 0;
//         else
//             return 1;
//     }
// }

// class Edge {
//     int v, w;

//     Edge(int v, int w) {
//         this.v = v;
//         this.w = w;
//     }
// }

// public class Main {
//     static int V, E, K;
//     static int[] distance;
//     static boolean[] check;
//     static ArrayList<Edge>[] edges;
//     static PriorityQueue<Vertex> queue;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         V = Integer.parseInt(st.nextToken());
//         E = Integer.parseInt(st.nextToken());
//         K = Integer.parseInt(br.readLine());
//         check = new boolean[V + 1];

//         distance = new int[V + 1];
//         edges = new ArrayList[V + 1];
//         for (int i = 0; i < V + 1; i++) {
//             distance[i] = Integer.MAX_VALUE;
//             edges[i] = new ArrayList<>();
//         }

//         for(int i = 0; i < E; i++) {
//             st = new StringTokenizer(br.readLine());

//             int u = Integer.parseInt(st.nextToken());
//             int v = Integer.parseInt(st.nextToken());
//             int w = Integer.parseInt(st.nextToken());

//             edges[u].add(new Edge(v, w));
//         }

//         dijkstra(K);

//         StringBuilder sb = new StringBuilder("");
//         for (int i = 1; i <= V; i++) {
//             if (check[i] == false) sb.append("INF\n");
//             else sb.append(distance[i] + "\n");
//         }

//         System.out.println(sb);
//     }

//     public static void dijkstra(int idx) {
//         queue = new PriorityQueue<Vertex>();
//         queue.offer(new Vertex(0, K));

//         while(!queue.isEmpty()) {
//             Vertex vertex = queue.poll();

//             if(check[vertex.idx] == true) continue;
//             check[vertex.idx] = true;

//             if(distance[vertex.idx] < vertex.distance) continue;
//             distance[vertex.idx] = vertex.distance;

//             for(int i = 0; i < edges[vertex.idx].size(); i++) {
//                 queue.offer(new Vertex(distance[vertex.idx] + edges[vertex.idx].get(i).w, edges[vertex.idx].get(i).v));
//             }
//         }
//     }
// }