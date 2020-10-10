import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, minDiff = Integer.MAX_VALUE;
    static int[] populations, group;
    static int[][] map;
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        populations = new int[N];
        group = new int[N];
        map = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            for(int j = 0; j < temp; j++) {
                int opp = Integer.parseInt(st.nextToken()) - 1;
                map[i][opp] = map[opp][i] = 1;
            }
        }

        grouping(0, 0);
        if(minDiff == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minDiff);
    }

    public static void grouping(int index, int count) {

        if(count == N) return;
        
        if(count != 0) {
            if(connected(0) && connected(1)) {
                cal();
            }
        }
        
        grouping(index + 1, count + 1);
        group[index] = 1;
        grouping(index + 1, count + 1);
        group[index] = 0;
    }

    public static void cal() {
        int[] sum = {0, 0};
        for(int i = 0; i < N; i++) {
            sum[group[i]] += populations[i];
        }

        minDiff = Math.min(minDiff, Math.abs(sum[0] - sum[1]));
    }

    public static boolean connected(int index) {
        queue = new LinkedList<Integer>();
        for(int i = 0; i < N; i++) {
            if(group[i] == index) {
                queue.offer(i);
                group[i] = -1;
                break;
            }
        }

        int current;
        while(!queue.isEmpty()) {
            current = queue.poll();
            for(int i = 0; i < N; i++) {
                if(map[current][i] == 1 && group[i] == index) {
                    queue.offer(i);
                    group[i] = -1;
                }
            }
        }

        // check and recover
        boolean check = true;
        for(int i = 0; i < N; i++) {
            if(group[i] == index) check = false;
            if(group[i] == -1) group[i] = index;
        }

        return check;
    }
}