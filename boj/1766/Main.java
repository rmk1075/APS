import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, count[];
    static ArrayList<Integer> problems[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = new int[N+1];
        problems = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) problems[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            problems[A].add(B);
            count[B]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i < N+1; i++) {
            if(count[i] == 0) pq.offer(i);
        }       

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int current = pq.poll();
            sb.append(current + " ");
            for(int i : problems[current]) {
                count[i]--;
                if(count[i] == 0) pq.offer(i);
            }
        }

        System.out.println(sb);
    }
}