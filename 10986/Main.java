import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[M];
        arr[0] = 1;

        st = new StringTokenizer(br.readLine());

        long s = 0;
        for(int i = 0; i < N; i++) {
            s = (s+Integer.parseInt(st.nextToken()))%M;
            arr[(int)s]++;
        }

        long result = 0L;
        for(int i = 0; i < M; i++) {
            result += (arr[i] * (arr[i] - 1)) / 2;
        }
        System.out.println(result);
    }
}