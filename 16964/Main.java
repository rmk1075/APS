import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, idx = 1, arr[];
    static boolean[] visited;
    static ArrayList<Integer>[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nodes = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1);
        System.out.println(1);
    }

    public static void dfs(int index) {
        if(idx == N) return ;

        ArrayList<Integer> candidate = new ArrayList<>();
        for(int node : nodes[index]) {
            if(visited[node]) continue;

            visited[node] = true;
            candidate.add(node);
        }

        int size = candidate.size();
        while(0 < size--) {
            if(candidate.contains(arr[idx])) {
                candidate.remove(Integer.valueOf(arr[idx]));
                dfs(arr[idx++]);
            } else {
                System.out.println(0);
                System.exit(0);
            }
        }
    }
}