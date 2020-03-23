import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int idx, c, d;

    Node(int idx, int c, int d) {
        this.idx = idx;
        this.c = c;
        this.d = d;
    }

    @Override
    public int compareTo(Node o) {
        return this.d - o.d;
    }
}

public class Main {
    static int T, map[][];
    static ArrayList<Node>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            edges = new ArrayList[N+1];
            for(int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
            
            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                edges[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            map = new int[N+1][M+1];
            for(int i = 2; i <= N; i++) Arrays.fill(map[i], Integer.MAX_VALUE);

            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.offer(new Node(1, 0, 0));

            while(!queue.isEmpty()) {
                Node cur = queue.poll();

                for(Node node : edges[cur.idx]) {
                    int dest = node.idx, cost = node.c + cur.c, distance = node.d + cur.d;
                    if(M < cost || map[dest][cost] <= distance) continue;
                    
                    queue.offer(new Node(dest, cost, distance));
                    while(cost <= M && distance < map[dest][cost]) {
                        map[dest][cost++] = distance;
                    }
                }
            }

            int minDistance = Integer.MAX_VALUE;
            for(int i = 1; i <= M; i++) minDistance = Math.min(minDistance, map[N][i]);

            sb.append(((minDistance == Integer.MAX_VALUE) ? "Poor KCM" : minDistance) + "\n");
        }
        System.out.println(sb);
    }
}