import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, A[], NGE[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        NGE = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        NGE[N-1][0] = -1;
        NGE[N-1][1] = N-1;
        for(int i = N-2; -1 < i; i--) {
            if(A[i] < A[i+1]) {
                NGE[i][0] = A[i+1];
                NGE[i][1] = i+1;
                continue;
            }

            int j = i+1;
            while(NGE[j][0] <= A[i] && NGE[j][0] != -1) j = NGE[j][1];
            NGE[i][0] = NGE[j][0];
            NGE[i][1] = NGE[j][1];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append(NGE[i][0] + " ");
        System.out.println(sb);
    }
}