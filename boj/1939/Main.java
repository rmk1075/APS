import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Bridge {
    int b, c;

    Bridge(int b, int c) {
        this.b = b;
        this.c = c;
    }
}

public class Main {
    static int N, M, src, dest, left = Integer.MAX_VALUE, right = 0;
    static boolean[] visited;
    static ArrayList<Bridge>[] bridges;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        bridges = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) bridges[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
            bridges[A].add(new Bridge(B, C));
            bridges[B].add(new Bridge(A, C));

            left = Math.min(left, C);
            right = Math.max(right, C);
        }

        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        int mid = left, ans = left;
        while(left <= right) {
            mid = (left + right) / 2;
            if(bfs(mid)) {
                ans = mid;
                left = mid+1;
            } else right = mid-1;
        }

        System.out.println(ans);
    }

    public static boolean bfs(int val) {
        for(int i = 1; i < N+1; i++) visited[i] = false;
        visited[src] = true;

        queue.offer(src);

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(Bridge bridge : bridges[current]) {
                if(bridge.c < val || visited[bridge.b]) continue;
                if(bridge.b == dest) {
                    queue.clear();
                    return true;
                }
                visited[bridge.b] = true;
                queue.offer(bridge.b);
            }
        }

        return false;
    }
}