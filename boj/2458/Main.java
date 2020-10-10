import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, count;
    static boolean[] visited, forward[], backward[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        forward = new boolean[N+1][N+1];
        backward = new boolean[N+1][N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            forward[A][B] = backward[B][A] = true;
        }

        int ans = 0;
        visited = new boolean[N+1];
        for(int i = 1; i < N+1; i++) {
            count = 0;
            Arrays.fill(visited, false);
            find(i, true);
            Arrays.fill(visited, false);
            find(i, false);
            ans += (count == N+1) ? 1 : 0;
        }
        System.out.println(ans);
    }

    public static void find(int index, boolean isForward) {
        count++;
        visited[index] = true;
        if(isForward) {
            for(int i = 1; i < N+1; i++) {
                if(!visited[i] && forward[index][i]) find(i, true);
            }
        } else {
            for(int i = 1; i < N+1; i++) {
                if(!visited[i] && backward[index][i]) find(i, false);
            }
        }
    }
}