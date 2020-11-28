import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int len = 2 * N;
        A = new int[len];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0, step = 0;
        boolean[] belt = new boolean[N];
        while (cnt < K) {
            step++;

            int temp = A[len - 1];
            for (int i = len - 1; 0 < i; i--) {
                A[i] = A[i - 1];
            }
            A[0] = temp;

            for (int i = N - 1; 0 < i; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = false;

            belt[N - 1] = false;
            for (int i = N - 1; 0 < i; i--) {
                if (belt[i] || A[i] == 0 || !belt[i - 1])
                    continue;

                belt[i] = belt[i - 1];
                belt[i - 1] = false;

                if (--A[i] == 0)
                    cnt++;
            }

            if (belt[0] || A[0] == 0)
                continue;

            belt[0] = true;
            if (--A[0] == 0)
                cnt++;
        }

        System.out.println(step);
    }
}