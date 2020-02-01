import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int N, M, K, minVal;
    static int[] visited;
    static int[][] A, command;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];

        K = Integer.parseInt(st.nextToken());
        command = new int[K][3];
        visited = new int[K];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            command[i][0] = Integer.parseInt(st.nextToken()) - 1;
            command[i][1] = Integer.parseInt(st.nextToken()) - 1;
            command[i][2] = Integer.parseInt(st.nextToken());
        }
        br.close();

        minVal = Integer.MAX_VALUE;
        dfs(0, new int[K]);

        System.out.println(minVal);
    }

    public static void dfs(int count, int[] order) {
        if(count == K) {
            int[][] arr = new int[N][M];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    arr[i][j] = A[i][j];
                }
            }

            for(int i = 0; i < K; i++) arr = rotate(arr, command[order[i]][0], command[order[i]][1], command[order[i]][2]);

            for(int i = 0; i < N; i++) {
                int sum = 0;
                for(int j = 0; j < M; j++) sum += arr[i][j];

                minVal = Math.min(minVal, sum);
            }

            return ;
        }

        for(int i = 0; i < K; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                order[count] = i;
                dfs(count + 1, order);
                visited[i] = 0;
            }
        }
    }

    public static int[][] rotate(int[][] arr, int r, int c, int s) {
       
        int[][] sub = new int[2 * s + 1][2 * s + 1];
        sub[s][s] = arr[r][c];

        for(int size = 1; size <= s; size++) {

            for(int i = 0; i < 2 * size; i++) {
                sub[s - size][s - size + 1 + i] = arr[r - size][c - size + i];
                sub[s - size + 1 + i][s + size] = arr[r - size + i][c + size];
                sub[s + size][s + size - 1 - i] = arr[r + size][c + size - i];
                sub[s + size - 1 - i][s - size] = arr[r + size - i][c - size];
            }
        }

        for(int i = 0; i < sub.length; i++) {
            for(int j = 0; j < sub.length; j++) {
                arr[r - s + i][c - s + j] = sub[i][j];
            }
        }

        return arr;
    }
}