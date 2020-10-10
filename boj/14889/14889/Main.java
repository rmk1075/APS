import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, minDiff = Integer.MAX_VALUE;
    static int[] team;
    static int[][] S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        team = new int[N];
        S = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        team(0, 0);
        
        System.out.println(minDiff);
    }

    public static void team(int index, int count) {

        if(count == N / 2) {
            // cal
            int sum0 = 0, sum1 = 0;
            for(int i = 0; i < N; i++) {
                if(team[i] == 0) {
                    for(int j = 0; j < N; j++) {
                        if(team[i] == team[j]) {
                            sum0 += S[i][j];
                        }
                    }
                } else {
                    for(int j = 0; j < N; j++) {
                        if(team[i] == team[j]) {
                            sum1 += S[i][j];
                        }
                    }
                }
            }

            minDiff = Math.min(minDiff, Math.abs(sum0 - sum1));

            return ;
        }

        if(index == N) return ;

        
        team(index + 1, count);
        team[index] = 1;
        team(index + 1, count + 1);
        team[index] = 0;
    }
}