import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, ans, picked, order[] = new int[11], players[][] = new int[11][11];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            for(int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++) players[i][j] = Integer.parseInt(st.nextToken());
            }

            ans = picked = 0;
            find(0);
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

    public static void find(int idx) {
        if(idx == 11) {
            int sum = 0;
            for(int i = 0; i < 11; i++) sum += players[order[i]][i];
            ans = Math.max(ans, sum);
            return ;
        }

        for(int i = 0; i < 11; i++) {
            if(players[i][idx] == 0 || (picked & (1 << i)) != 0) continue;
            order[idx] = i;
            picked |= (1 << i);
            find(idx+1);
            picked &= ~(1 << i);
        }
    }
}