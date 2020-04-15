import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int dest, dist;

    Edge(int dest, int dist) {
        this.dest = dest;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
        return (this.dist < o.dist) ? -1 : 1;
    }
}

public class Main {
    static int N, M, src, dest, INF = Integer.MAX_VALUE;
    static ArrayList<Edge>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) edges[i] = new ArrayList<>();

        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N+1];
        Edge[] dist = new Edge[N+1];
        for(int i = 1; i <= N; i++) {
            if(i == src) dist[i] = new Edge(i, 0);
            else dist[i] = new Edge(i, INF);
            pq.offer(dist[i]);
        }

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            for(Edge next : edges[edge.dest]) {
                if(!check[next.dest] && dist[edge.dest].dist + next.dist < dist[next.dest].dist) {
                    pq.remove(dist[next.dest]);
                    dist[next.dest].dist = dist[edge.dest].dist + next.dist;
                    pq.offer(dist[next.dest]);
                }
            }

            check[edge.dest] = true;
        }

        System.out.println(dist[dest].dist);
    }
}