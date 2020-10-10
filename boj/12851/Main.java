import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N == K) {
            System.out.println(0);
            System.out.println(1);
            System.exit(0);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        arr[N] = 1;

        int current, size, time = 1, count = 0;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                current = queue.poll();

                if(0 <= current-1 && time <= arr[current-1]) {
                    if(current-1 == K) {
                        count++;
                    } else {
                        arr[current-1] = time;
                        queue.offer(current-1);
                    }
                }

                if(K < current) continue;

                if(current+1 < 100001 && time <= arr[current+1]) {
                    if(current+1 == K) {
                        count++;
                    } else {
                        arr[current+1] = time;
                        queue.offer(current+1);
                    }
                }

                if(2*current < 100001 && time <= arr[2*current]) {
                    if(2*current == K) {
                        count++;
                    } else {
                        arr[current*2] = time;
                        queue.offer(current*2);
                    }
                }
            }

            if(count != 0) break;

            time++;
        }

        System.out.println(time);
        System.out.println(count);
    }
}