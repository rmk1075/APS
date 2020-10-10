import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int N, maxVal;
    static int[] works;
    static int[][] schedule;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        works = new int[N];
        schedule = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        maxVal = 0;
        dfs(0);

        System.out.println(maxVal);
    }

    public static void dfs(int count) {

        if(count == N) {

            int sum = 0;
            int last = 0;
            int time = 0;
            for(int i = 0; i < N; i++) {

                if(0 < time) {
                    time--;
                    continue;
                }

                if(works[i] == 1) {
                    time += schedule[i][0] - 1;
                    sum += schedule[i][1];
                    last = schedule[i][1];
                }

            }

            if(0 < time) sum -= last;

            maxVal = Math.max(maxVal, sum);
            return ;
        }

        dfs(count + 1);
        works[count] = 1;
        dfs(count + 1);
        works[count] = 0;

    }
}