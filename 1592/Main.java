import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M, L;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int idx = 0, count = 0;
        while(arr[idx] != M - 1) {
            arr[idx]++;
            if(arr[idx] % 2 == 0) {
                idx = (idx - L + N) % N;
            } else {
                idx = (idx + L) % N;
            }
            count++;
        }

        System.out.println(count);
    }
}