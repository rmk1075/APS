import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int v;
    long cost;

    Edge(int v, long cost) {
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.cost, o.cost);
    }
}

public class Main {
    static int N, M, A, B, INF = Integer.MAX_VALUE, backward[];
    static long[] costs;
    static List<Edge> graph[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        backward = new int[N + 1];
        costs = new long[N + 1];
        graph = new List[N + 1];
        for(int i = 1; i < N + 1; i++) {
            costs[i] = INF;
            graph[i] = new ArrayList<>();
        }
        
        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        costs[A] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(A, 0));
        while(!pq.isEmpty()) {
            Edge current = pq.poll();
            if(costs[current.v] < current.cost) continue;
            for(Edge edge : graph[current.v]) {
                if(costs[edge.v] <= edge.cost + current.cost) continue;
                costs[edge.v] = edge.cost + current.cost;
                backward[edge.v] = current.v;
                pq.offer(new Edge(edge.v, costs[edge.v]));
            }
        }

        int loc = B;
        ArrayList<Integer> list = new ArrayList<>();
        while(loc != A) {
            list.add(loc);
            loc = backward[loc];
        }
        list.add(A);
        
        StringBuilder sb = new StringBuilder();
        sb.append(costs[B] + "\n" + list.size() + "\n");
        for(int i = list.size() - 1; -1 < i; i--) sb.append(list.get(i) + " ");
        System.out.println(sb);
    }
}