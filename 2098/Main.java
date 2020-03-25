import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][1 << N]; // {current, visited}
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], 16000001);
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(find(0, 1));
    }

    public static int find(int cur, int visited) {
        if(visited == (1 << N)-1) return (map[cur][0] == 0) ? 16000001 : map[cur][0];
        if(dp[cur][visited] != 16000001) return dp[cur][visited];

        for(int i = 0; i < N; i++) {
            if((visited & (1 << i)) != 0 || map[cur][i] == 0) continue;
           dp[cur][visited] = Math.min(dp[cur][visited], find(i, (visited | (1 << i))) + map[cur][i]);
        }

        return dp[cur][visited];
    }
}