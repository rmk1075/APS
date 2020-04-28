import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, items[][], bags[];
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o2[1] - o1[1];
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        items = new int[N][2];
        bags = new int[K];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < K; i++) bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(bags);

        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        long ans = 0;
        int j = 0;
        for(int i = 0; i < K; i++) {
            while(j < N && items[j][0] <= bags[i]) pq.offer(items[j++]);
            if(pq.size() != 0) ans += pq.poll()[1];
        }

        System.out.println(ans);
    }
}