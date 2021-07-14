import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] depth;
    static int[][] parent;
    static List<Integer>[] tree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++) tree[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        // find max depth
        int temp = 1;
        K = 0;
        while(temp <= N) {
            temp <<= 1;
            K++;
        }

        depth = new int[N + 1];
        parent = new int[N + 1][K];

        // find each depth
        findDepth(1, 1);

        // find parent
        findParent();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(findAncestor(a, b) + "\n");
        }

        System.out.println(sb);
    }

    public static void findDepth(int index, int d) {
        depth[index] = d;
        for(int next : tree[index]) {
            if(depth[next] != 0) continue;
            findDepth(next, d + 1);
            parent[next][0] = index;
        }
    }

    public static int findAncestor(int a, int b) {
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        for(int i = K - 1; -1 < i; i--) {
            if(Math.pow(2, i) <= (depth[a] - depth[b])) a = parent[a][i];
        }

        if(a == b) return a;

        for(int i = K - 1; -1 < i; i--) {
            if(parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    public static void findParent() {
        for(int i = 1; i < K; i++) {
            for(int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }
}
