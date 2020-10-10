import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int a, b, l;

    Edge(int a, int b, int l) {
        this.a = a;
        this.b = b;
        this.l = l;
    }

    @Override
    public int compareTo(Edge o) {
        return this.l - o.l;
    }
}

public class Main {
    static int V, E, parent[] = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= V; i++) parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int cnt = 0, ans = 0;
        while(cnt < V-1 && !pq.isEmpty()) {
            Edge edge = pq.poll();
            if(find(edge.a) == find(edge.b)) continue;
            parent[find(edge.a)] = find(edge.b);
            ans += edge.l;
            cnt++;
        }

        System.out.println(ans);
    }

    public static int find(int n) {
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }
}