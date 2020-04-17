import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[4][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) arr[j][i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[N*N], CD = new int[N*N];
        int idx = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                AB[idx] = arr[0][i] + arr[1][j];
                CD[idx++] = arr[2][i] + arr[3][j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);
        int left = 0, right = N*N-1, len = N*N;
        long ans = 0;
        while(left < N*N && -1 < right) {
            long lval = AB[left], rval = CD[right];
            if(lval + rval == 0) {
                long lcnt = 0;
                while(left < len && AB[left] == lval) {
                    lcnt++;
                    left++;
                }

                long rcnt = 0;
                while(-1 < right && CD[right] == rval) {
                    rcnt++;
                    right--;
                }
                ans += lcnt * rcnt;
            }

            if(lval + rval < 0) left++;
            if(0 < lval + rval) right--;
        }
        System.out.println(ans);
    }
}