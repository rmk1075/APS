import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}

public class Main {
    static int M, N, total, parents[] = new int[200001];
    static PriorityQueue<Edge> edges = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        while(M != 0) {
            edges.clear();
            total = 0;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
                edges.offer(new Edge(u, v, w));
                total += w;
            }

            int cnt = 0, ans = 0;
            while (cnt < M - 1) {
                Edge edge = edges.poll();
                int a = find(edge.u), b = find(edge.v);

                if (a != b) {
                    parents[b] = a;
                    ans += edge.w;
                    cnt++;
                }
            }

            sb.append(total - ans + "\n");

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }

    public static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }
}