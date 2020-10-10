import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, count[];
    static ArrayList<Integer> nums[];
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = new int[N+1];
        nums = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++) nums[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            nums[A].add(B);
            count[B]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++) {
            if(count[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int n = queue.poll();
            sb.append(n + " ");

            for(int i : nums[n]) {
                count[i]--;
                if(count[i] == 0) queue.offer(i);
            }
        }

        System.out.println(sb);
    }
}