import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        // 9876543210 => 10 digits
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(1022 < N) {
            System.out.println(-1);
            return ;
        }
        
        if(N < 10) {
            System.out.println(N);
            return ;
        }

        Queue<Long> queue = new LinkedList<>();
        for(int i = 1; i < 10; i++) queue.offer(Long.valueOf(i));
        
        int cnt = 9;
        while(cnt < N) {
            long num = queue.poll();
            long temp = num % 10;
            for(int i = 0; i < temp; i++) {
                queue.offer(num * 10 + i);
                if(++cnt == N) {
                    System.out.println(num * 10 + i);
                    return ;
                }
            }
        }

    }
}