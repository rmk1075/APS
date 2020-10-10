import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int x, y, weight;

    Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

public class Main {
    static int N, c = 1, map[][], coins[][];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        while(N != 0) {
            map = new int[N][N];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++)  map[i][j] = Integer.parseInt(st.nextToken());
            }
            
            coins = new int[N][N];
            for(int i = 0; i < N; i++) Arrays.fill(coins[i], Integer.MAX_VALUE);
            coins[0][0] = map[0][0];
            pq.offer(new Node(0, 0, coins[0][0]));
    
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                if(coins[cur.x][cur.y] < cur.weight) continue;
    
                for(int d = 0; d < 4; d++) {
                    int x = cur.x + dx[d], y = cur.y + dy[d];
                    if(x < 0 || y < 0 || x == N || y == N || coins[x][y] <= cur.weight + map[x][y]) continue;
    
                    coins[x][y] = cur.weight + map[x][y];
                    pq.offer(new Node(x, y, coins[x][y]));
                }
            }
            sb.append("Problem " + c++ + ": " + coins[N-1][N-1] + "\n");
            N = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
    }
}