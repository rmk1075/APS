import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int N, M, nodes[], edges[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new int[N+1];
        edges = new int[N+1][N+1];
        for(int i = 0; i <= N; i++) {
            nodes[i] = Integer.MAX_VALUE;
            Arrays.fill(edges[i], Integer.MAX_VALUE);
        }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            edges[u][v] = Math.min(edges[u][v], w);
        }

        nodes[1] = 0;
        int count = 0;
        boolean isUpdated = true;
        while(count++ < N && isUpdated) {
            isUpdated = false;
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(edges[i][j] == Integer.MAX_VALUE || nodes[i] == Integer.MAX_VALUE || nodes[j] <= nodes[i] + edges[i][j]) continue;
                    nodes[j] = nodes[i] + edges[i][j];
                    isUpdated = true;
                }
            }
        }

        if(isUpdated) {
            System.out.println(-1);
            System.exit(0);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++) sb.append(((nodes[i] == Integer.MAX_VALUE) ? "-1\n" : nodes[i]+"\n"));
        System.out.println(sb);
    }
}