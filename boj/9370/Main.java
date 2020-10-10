import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int idx, distance;

    Node(int idx, int distance) {
        this.idx = idx;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}

public class Main {
    static int T, nodes[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());

            int[][] map = new int[n+1][n+1];
            for(int i = 0; i <= n; i++) Arrays.fill(map[i], -1);
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
                map[a][b] = map[b][a] = d*2;
            }
            map[g][h] = map[h][g] = map[h][g]-1;
            
            boolean[] check = new boolean[n+1];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(s, 0));
            
            int[] distance = new int[n+1];
            for(int i = 0; i <= n; i++) distance[i] = Integer.MAX_VALUE;
            distance[s] = 0;

            while(!pq.isEmpty()) {
                Node current = pq.poll();
                int cur = current.idx;
                if(check[cur]) continue;
                check[cur] = true;

                for(int i = 1; i <= n; i++) {
                    if(map[cur][i] == -1 || check[i] == true || distance[i] <= distance[cur] + map[cur][i]) continue;
                    distance[i] = distance[cur] + map[cur][i];
                    pq.offer(new Node(i, distance[i]));
                }
            }

            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < t; i++) {
                int candidate = Integer.parseInt(br.readLine());
                if(distance[candidate] != Integer.MAX_VALUE && distance[candidate] % 2 != 0) list.add(candidate);
            }

            Collections.sort(list);
            for(int v : list) sb.append(v + " ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}