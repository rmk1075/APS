import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] arr = new int[2][500001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(arr[0], -1);
        Arrays.fill(arr[1], -1);
        arr[0][N] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        int size, count = 1;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                int current = queue.poll();
                int c = count % 2, v = current - 1;

                if(-1 < current-1 && arr[c][v] == -1) {
                    arr[c][v] = count;
                    queue.offer(v);
                }

                v = current + 1;
                if(v < 500001 && arr[c][v] == -1) {
                    arr[c][v] = count;
                    queue.offer(v);
                }

                v = current*2;
                if(v < 500001 && arr[c][v] == -1) {
                    arr[c][v] = count;
                    queue.offer(v);
                }
            }

            count++;
        }
        
        int ans = Integer.MAX_VALUE;
        count = 0;
        while(true) {
            K += count;
            if(500000 < K) break;
            if(arr[count%2][K] != -1 && arr[count%2][K] <= count) {
                ans = Math.min(ans, count);
                break;
            }
            count++;
        }

        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
    }
}