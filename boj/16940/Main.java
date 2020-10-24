import java.io.*;
import java.util.*;

public class Main {
    static int N, order[];
    static List<Integer> tree[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];
        for(int i = 1; i < N + 1; i++) tree[i] = new ArrayList<>();

        StringTokenizer st;
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        order = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) order[i] = Integer.parseInt(st.nextToken());

        if(order[0] != 1) {
            System.out.println(0);
            return ;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        int left = 1, right = 1;
        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int i : tree[current]) {
                if(visited[i]) continue;
                visited[i] = true;
                right++;
            }

            for(int i = left; i < right; i++) {
                if(visited[order[i]]) {
                    queue.offer(order[i]);
                } else {
                    System.out.println(0);
                    return ;
                }
            }

            left = right;
        }

        System.out.println(1);
        return ;
    }
}