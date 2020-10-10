import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int L, N;
    static int[] cake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        L = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        cake = new int[L + 1];
        int maxWntLength = 0, maxWntIdx = 0;
        int maxLength = 0, maxIdx = 0;
        int p, k;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if(maxWntLength < k - p) {
                maxWntLength = k - p;
                maxWntIdx = i;
            }

            int tempCake = 0;
            for(int j = p; j <= k; j++) {
                if(cake[j] == 0) {
                    cake[j] = 1;
                    tempCake++;
                }
            }

            if(maxLength < tempCake) {
                maxLength = tempCake;
                maxIdx = i;
            }

        }

        System.out.println(maxWntIdx + 1);
        System.out.println(maxIdx + 1);

    }
}