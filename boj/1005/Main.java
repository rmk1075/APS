import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int T;
    private static int N;
    private static int K;
    private static int W;
    private static int[] D;
    private static int[] mem;
    private static List<Integer>[] prerequisites;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (0 < T--) {
            init(br);
            System.out.println(find(W));
        }
    }

    private static int find(int target) {
        if (mem[target] != -1) {
            return mem[target];
        }

        List<Integer> prerequisite = prerequisites[target];
        int time = 0;
        for (int pre : prerequisite) {
            time = Math.max(time, find(pre));
        }
        time += D[target];
        mem[target] = time;
        return time;
    }

    private static void init(BufferedReader br) throws NumberFormatException, IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = new int[N];
        prerequisites = new List[N];
        for (int i = 0; i < N; i++) {
            prerequisites[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            prerequisites[y].add(x);
        }

        W = Integer.parseInt(br.readLine()) - 1;

        mem = new int[N];
        Arrays.fill(mem, -1);
    }
}