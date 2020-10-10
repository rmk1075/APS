import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        distance = new int[N+1][N+1];

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            if(distance[a][b] != 0 && distance[a][b] <= c) continue;
            distance[a][b] = c;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(distance[j][i] == 0) continue;

                for(int k = 1; k <= N; k++) {
                    if(j == k || distance[i][k] == 0 || (distance[j][k] != 0 && distance[j][k] <= distance[j][i] + distance[i][k])) continue;
                    distance[j][k] = distance[j][i] + distance[i][k];
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) System.out.print(distance[i][j] + " ");
            System.out.println();
        }
    }
}