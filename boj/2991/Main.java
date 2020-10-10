import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A, B, C, D, P, M, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        System.out.println((((0 < P % (A + B)) && (P % (A + B) <= A)) ? 1 : 0) + (((0 < P % (C + D)) && (P % (C + D) <= C)) ? 1 : 0));
        System.out.println((((0 < M % (A + B)) && (M % (A + B) <= A)) ? 1 : 0) + (((0 < M % (C + D)) && (M % (C + D) <= C)) ? 1 : 0));
        System.out.println((((0 < N % (A + B)) && (N % (A + B) <= A)) ? 1 : 0) + (((0 < N % (C + D)) && (N % (C + D) <= C)) ? 1 : 0));
    }
}