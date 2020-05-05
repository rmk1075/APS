import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, src, ans, nums[] = new int[100001];
    static boolean[] group = new boolean[100001], visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < N+1; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                group[i] = visited[i] = false;
            }

            ans = 0;
            for(int i = 1; i < N+1; i++) find(i);

            sb.append(N - ans + "\n");
        }
        System.out.println(sb);
    }

    public static void find(int idx) {
        if(visited[idx]) return ;
        visited[idx] = true;

        int num = nums[idx];
        if(!visited[num]) find(num);
        else {
            if(!group[num]) {
                ans++;
                for(int i = num; i != idx; i = nums[i]) ans++;
            }
        }

        group[idx] = true;
    }
}