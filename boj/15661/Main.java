import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, minDiff;
    static int[] team;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        team = new int[N];
        arr = new int[N][N];

        int sum = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum += arr[i][j];
            }
        }

        minDiff = sum;

        dfs(0, 0, sum);
        System.out.println(minDiff);
    }

    public static void dfs(int count, int sum1, int sum2) {
    
        if(sum1 != 0) minDiff = Math.min(minDiff, Math.abs(sum2 - sum1));

        if(count == N-1) return ;

        dfs(count+1, sum1, sum2);

        team[count] = 1;
        for(int i = 0; i < N; i++) {
            if(team[i] == 0) {
                sum2 -= (arr[i][count] + arr[count][i]);
            } else {
                sum1 += arr[i][count] + arr[count][i];
            }
        }

        dfs(count+1, sum1, sum2);
        team[count] = 0;
    }
}