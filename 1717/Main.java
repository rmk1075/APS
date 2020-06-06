import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for(int i = 1; i < N+1; i++) arr[i] = i;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());

            if(a == 0) arr[find(b)] = find(c);
            else System.out.println((find(b) == find(c)) ? "YES" : "NO");
        }
    }

    public static int find(int idx) {
        if(arr[idx] == idx) return idx;
        return arr[idx] = find(arr[idx]);
    }
}