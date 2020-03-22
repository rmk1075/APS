import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class Main {
    static int N, M, nodes[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new int[N+1];
        Arrays.fill(nodes, Integer.MAX_VALUE);
        
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        nodes[1] = 0;
        int count = 0;
        boolean isUpdated = true;
        while(isUpdated && count <= N) {
            isUpdated = false;
            
            for(Edge edge : edges) {
                if(nodes[edge.src] == Integer.MAX_VALUE || nodes[edge.dest] <= nodes[edge.src] + edge.weight) continue;
                
                nodes[edge.dest] = nodes[edge.src] + edge.weight;
                isUpdated = true;
            }
            count++;
        }

        if(N < count) {
            System.out.println(-1);
            System.exit(0);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++) sb.append(((nodes[i] == Integer.MAX_VALUE) ? -1 : nodes[i])).append("\n");
        System.out.println(sb);
    }
}