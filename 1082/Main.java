import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, price[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) price[i] = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        int minIdx = 0, minVal = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++) {
            if(price[i] < minVal) {
                minIdx = i;
                minVal = price[i];
            }
        }

        if(M < minVal) {
            System.out.println(0);
            return ;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minIdx);
        M -= minVal;

        if(price[0] < minVal) {
            minIdx = 0;
            minVal = price[0];
        }

        int cnt = M / minVal;
        M -= cnt * minVal;

        for(int i = 0; i < cnt; i++) sb.append(minIdx);

        for(int i = 0; i < sb.length(); i++) {
            int temp = sb.charAt(i) - '0';
            for(int j = N-1; temp < j; j--) {
                if(price[j] <= price[temp] + M) {
                    M -= price[j] - price[temp];
                    sb.replace(i, i+1, j + "");
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}