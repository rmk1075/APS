import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Edge {
    int src, dest, time;
    Edge(int src, int dest, int time) {
        this.src = src;
        this.dest = dest;
        this.time = time;
    }
}

public class Main {
    static int N, M, INF = Integer.MAX_VALUE, nodes[];
    static ArrayList<Edge> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new int[N+1];
        for(int i = 1; i < N+1; i++) nodes[i] = INF;
        nodes[1] = 0;
        
        edges = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        for(int i = 0; i < N; i++) {
            for(Edge edge : edges) {
                if(edge.time < 0 && nodes[edge.src] <= Integer.MIN_VALUE - edge.time) {
                    System.out.println(-1);
                    return ;
                }
                if(nodes[edge.src] == INF || nodes[edge.dest] <= nodes[edge.src] + edge.time) continue;
                nodes[edge.dest] = nodes[edge.src] + edge.time;
            }
        }
        
        for(Edge edge : edges) {
            if(nodes[edge.src] == INF || nodes[edge.dest] <= nodes[edge.src] + edge.time) continue;
            System.out.println(-1);
            return ;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < N+1; i++) sb.append((nodes[i] == INF ? -1 : nodes[i]) + "\n");
        System.out.println(sb);

    }
}