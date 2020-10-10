import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] computers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        computers = new int[N+1];
        for(int i = 1; i < N+1; i++) {
            computers[i] = i;
        }

        int a, b;
        M = Integer.parseInt(br.readLine());
        while(0 < M--) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            computers[find(a)] = find(b);
        }

        int num = find(1);
        int count = 0;
        for(int i = 1; i < N + 1; i++) {
            if(find(i) == num) count++;
        }

        System.out.println(count - 1);
    }

    public static int find(int a) {
        if(computers[a] == a) return a;

        return computers[a] = find(computers[a]);
    }
}