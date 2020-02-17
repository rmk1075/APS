import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, E;
    static int[] nodes, check;
    static int[][] graph, distances;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new int[N+1][N+1];
        distances = new int[3][N+1];

        int a, b, c;
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a][b] = graph[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        dijkstra(1, 0);
        dijkstra(a, 1);
        dijkstra(b, 2);

        int distance1 = (distances[0][a] == Integer.MAX_VALUE || distances[1][b] == Integer.MAX_VALUE || distances[2][N] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : distances[0][a] + distances[1][b] + distances[2][N];
        int distance2 = (distances[0][b] == Integer.MAX_VALUE || distances[1][N] == Integer.MAX_VALUE || distances[2][a] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : distances[0][b] + distances[1][N] + distances[2][a];
        int distance = Math.min(distance1, distance2);

        if(distance == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(distance);
    }

    public static void dijkstra(int src, int index) {
        check = new int[N+1];
        for(int i = 0; i < N+1; i++) distances[index][i] = Integer.MAX_VALUE;

        distances[index][src] = 0;
        check[src] = 1;

        for(int i = 1; i < N+1; i++) {
            if(check[i] == 0 && graph[src][i] != 0) {
                distances[index][i] = graph[src][i];
            }
        }

        int minVal, minNode;
        for(int n = 0; n < N-1; n++) {
            minNode = 0;
            minVal = Integer.MAX_VALUE;

            // find minimum node
            for(int i = 1; i < N+1; i++) {
                if(check[i] == 0 && distances[index][i] < minVal) {
                    minNode = i;
                    minVal = distances[index][i];
                }
            }

            if(minVal == Integer.MAX_VALUE) continue;

            // check the every unvisited nodes which connected to the minNode
            for(int i = 0; i < N+1; i++) {
                if(check[i] == 0 && graph[minNode][i] != 0) {
                    if(distances[index][minNode] + graph[minNode][i] < distances[index][i]) {
                        distances[index][i] = distances[index][minNode] + graph[minNode][i];
                    }
                }
            }

            // change the value of minNode and check - true(1)
            check[minNode] = 1;
        }
    }
}