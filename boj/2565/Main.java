import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] lines = new int[501], dp = new int[501];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        int maxCount = 0;
        for(int i = 1; i < 501; i++) {
            if(lines[i] == 0) continue;

            int count = 1;
            for(int j = i; 0 < j; j--) {
                if(lines[j] == 0) continue;
                if(lines[j] < lines[i]) count = Math.max(count, dp[j] + 1);
            }
            dp[i] = count;
            maxCount = Math.max(maxCount, count);
        }

        System.out.println(n - maxCount);
    }
}