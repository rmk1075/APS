import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S, head, tail, sum, minLength = Integer.MAX_VALUE;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        // init
        head = tail = 0;
        while(sum < S) {
            sum += nums[tail++];
            if(tail == N) break;
        }

        // never success
        if(sum < S) {
            System.out.println(0);
            System.exit(0);
        }
        
        minLength = tail;
        while(tail < N) {
            if(S <= sum) {
                minLength = Math.min(minLength, tail-head);

                if(minLength == 1) break;

                sum -= nums[head];
                head++;
            } else {
                sum += nums[tail++];
            }
        }
        
        while(S <= sum) {
            minLength = Math.min(minLength, tail-head);
            sum -= nums[head++];
        }

        System.out.println(minLength);
    }
}