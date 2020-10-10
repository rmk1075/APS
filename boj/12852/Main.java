import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int N, nums[];
    static Deque<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        nums = new int[N+1];

        queue.offer(N);
        int size, time = 1;
        loop: while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                int num = queue.poll();
                if(num % 3 == 0 && 0 < num / 3 && nums[num / 3] == 0) {
                    if(num / 3 == 1) break loop;
                    nums[num / 3] = time;
                    queue.offer(num / 3);
                }

                if(num % 2 == 0 && 0 < num / 2 && nums[num / 2] == 0) {
                    if(num / 2 == 1) break loop;
                    nums[num / 2] = time;
                    queue.offer(num / 2);
                }

                if(0 < num - 1 && nums[num - 1] == 0) {
                    if(num - 1 == 1) break loop;
                    nums[num - 1] = time;
                    queue.offer(num - 1);
                }
            }
            time++;
        }
        queue.clear();

        nums[1] = time + 1;
        sb.append(time + "\n");
        int cur = 1;
        while(true) {
            queue.offer(cur);

            if(cur == N) break;

            if(cur * 3 <= N && nums[cur * 3] == time - 1) {
                cur *= 3;
                time--;
                continue;
            }

            if(cur * 2 <= N && nums[cur * 2] == time - 1) {
                cur *= 2;
                time--;
                continue;
            }

            if(cur + 1 <= N && nums[cur + 1] == time - 1) {
                cur += 1;
                time--;
            }
        }

        while(!queue.isEmpty()) sb.append(queue.pollLast() + " ");
        System.out.println(sb);
    }
}