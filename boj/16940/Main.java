import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, order[];
    static ArrayList<Integer>[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();

        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);            
        }

        order = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) order[i] = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        int idx = 1;
        while(!queue.isEmpty()) {
            int current = queue.poll();

            ArrayList<Integer> list = new ArrayList<>();
            for(int node : nodes[current]) {
                if(visited[node]) continue;
                visited[node] = true;
                list.add(node);
            }

            int size = list.size();
            for(int i = 0; i < size; i++) {
                if(list.contains(order[idx])) {
                    visited[order[idx]] = true;
                    list.remove(Integer.valueOf(order[idx]));
                    queue.add(order[idx++]);
                } else {
                    System.out.println(0);
                    System.exit(0);
                }
            }
        }

        System.out.println(1);
    }
}