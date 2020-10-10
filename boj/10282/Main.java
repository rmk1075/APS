import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Com implements Comparable<Com> {
    int idx, time;

    Com(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }

    @Override
    public int compareTo(Com o) {
        return this.time - o.time;
    }
}

public class Main {
    static int T, N, D, C, INF = 987654321, times[] = new int[10001];
    static ArrayList<Com>[] coms = new ArrayList[10001];
    static PriorityQueue<Com> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            for(int i = 1; i < N+1; i++) {
                times[i] = INF;
                coms[i] = new ArrayList<>();
            }

            for(int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                coms[b].add(new Com(a, Integer.parseInt(st.nextToken())));
            }

            pq.clear();
            pq.offer(new Com(C, 0));
            times[C] = 0;
            int cnt = 1, maxTime = 0;
            while(!pq.isEmpty()) {
                Com com = pq.poll();
                for(Com next : coms[com.idx]) {
                    if(times[com.idx] + next.time < times[next.idx]) {
                        if(times[next.idx] == INF) cnt++;
                        times[next.idx] = times[com.idx] + next.time;
                        pq.offer(new Com(next.idx, times[next.idx]));
                    }
                }
            }
            
            for(int i = 1; i < N+1; i++) {
                if(times[i] < INF) maxTime = Math.max(maxTime, times[i]);
            }
            sb.append(cnt + " " + maxTime + "\n");
        }
        System.out.println(sb);
    }    
}