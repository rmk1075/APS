import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int v, w;

    Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}

public class Main {
    static int N, M, minLen = Integer.MAX_VALUE, INF = Integer.MAX_VALUE, parents[];
    static ArrayList<Edge> graph[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        boolean[] check = new boolean[N+1];
        Edge[] edges = new Edge[N+1];
        edges[1] = new Edge(1, 0);
        queue.offer(edges[1]);
        for(int i = 2; i <= N; i++) edges[i] = new Edge(i, INF);

        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            if(edges[edge.v].w < edge.w) continue;

            for(Edge next : graph[edge.v]) {
                if(check[next.v] || edges[next.v].w <= edges[edge.v].w + next.w) continue;
                parents[next.v] = edge.v;
                edges[next.v].w = edges[edge.v].w + next.w;
                queue.offer(edges[next.v]);
            }
            check[edge.v] = true;
        }

        System.out.println(N-1);
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++) sb.append(i + " " + parents[i] + "\n");
        System.out.println(sb);
    }
}