import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int a = 0; a <= N; a++) {
            int b = N - a;

            if(a*b < K) continue;

            int[] A = new int[b+1];
            for(int i = 0; i < a; i++) {
                int idx = b < K ? b : K;

                A[idx]++;
                K -= idx;
            }
            

            for(int i = b; 0 <= i; i--) {
                for(int j = 0; j < A[i]; j++) {
                    System.out.print('A');
                }
                if(0 < i) System.out.print('B');
            }

            return ;
        }

        System.out.println(-1);
    }
}