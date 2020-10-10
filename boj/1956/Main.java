import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int V, E, edges[][], map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new int[V+1][V+1];
        for(int i = 1; i <= V; i++) Arrays.fill(map[i], INF);

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {
                if(map[i][j] == INF) continue;

                for(int k = 1; k <= V; k++) {
                    if(map[k][i] == INF) continue;
                    if(map[k][j] <= map[k][i] + map[i][j]) continue;
                    map[k][j] = map[k][i] + map[i][j];
                }
            }
        }

        int minDistance = INF;
        for(int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {
                if(i == j || map[i][j] == INF || map[j][i] == INF) continue;
                minDistance = Math.min(minDistance, map[i][j] + map[j][i]);
            }
        }

        System.out.println((minDistance == INF) ? -1 : minDistance);
    }
}