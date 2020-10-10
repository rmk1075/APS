import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, count[];
    static LinkedList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = new int[N+1];
        list = new LinkedList[N+1];
        for(int i = 1; i < N+1; i++) list[i] = new LinkedList<>();
        while(0 < M--) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken()), v1 = Integer.parseInt(st.nextToken());
            while(1 < len--) {
                int v2 = Integer.parseInt(st.nextToken());
                list[v1].add(v2);
                count[v2]++;
                v1 = v2;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++) {
            if(count[i] == 0) {
                sb.append(i + "\n");
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int i : list[current]) {
                if(--count[i] == 0) {
                    sb.append(i + "\n");
                    queue.offer(i);
                }
            }
        }

        for(int i = 1; i < N+1; i++) {
            if(count[i] != 0) {
                System.out.println(0);
                return ;
            }
        }

        System.out.println(sb);
    }
}