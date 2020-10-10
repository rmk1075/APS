import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, S, arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        while(0 < K--) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        for(int k = 1; k < N+1; k++) {
            for(int i = 1; i < N+1; i++) {
                for(int j = 1; j < N+1; j++) {
                    if(i == j || arr[i][j] == 1) continue;
                    if(arr[i][j] + 1 < arr[i][k] + arr[k][j]) arr[i][j] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        S = Integer.parseInt(br.readLine());
        while(0 < S--) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), result = 0;            
            if(0 < arr[a][b]) result = -1;
            if(0 < arr[b][a]) result = 1;
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }
}