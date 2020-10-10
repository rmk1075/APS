import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int u, v;
    double w;

    Edge(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return (this.w < o.w) ? -1 : 1;
    }
}

public class Main {
    static int N, parents[];
    static float nodes[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        nodes = new float[N][2];
        StringTokenizer st;

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            parents[i] = i;
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken()), y = Float.parseFloat(st.nextToken());
            nodes[i][0] = x;
            nodes[i][1] = y;

            for(int j = 0; j < i; j++) {
                edges.offer(new Edge(i, j, Math.sqrt(Math.pow(nodes[j][0] - x, 2) + Math.pow(nodes[j][1] - y, 2))));
            }
        }

        int cnt = 0;
        double ans = 0;
        while(cnt < N-1) {
            Edge edge = edges.poll();
            int a = find(edge.u), b = find(edge.v);

            if(a != b) {
                parents[b] = a;
                ans += edge.w;
                cnt++;
            }
        }

        System.out.println(ans);
    }

    public static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }
}