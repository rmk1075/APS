import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, K;
    static int[] ans, visited = new int[100001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sc.close();

        if(N == K) {
            System.out.println(0);
            System.out.println(N);
            System.exit(0);
        }

        for(int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N] = N;

        int size, current, time = 1;
        loop:
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                current = queue.poll();
    
                if(0 <= current-1 && visited[current-1] == -1) {
                    visited[current-1] = current;
                    
                    if(current-1 == K) break loop;
                    queue.offer(current-1);
                }

                if(K < current) continue;

                if(current+1 <= K && visited[current+1] == -1) {
                    visited[current+1] = current;

                    if(current+1 == K) break loop;
                    queue.offer(current+1);
                }

                if(current*2 < 100001 && visited[current*2] == -1) {
                    visited[current*2] = current;

                    if(current*2 == K) break loop;
                    queue.offer(current*2);
                }
            }

            time++;
        }
        
        System.out.println(time);
        
        ans = new int[time+1];
        int idx = 0;
        while(K != N) {
            ans[idx++] = K;
            K = visited[K];
        }
        ans[time] = N;
        for(int i = time; -1 < i; i--) {
            System.out.print(ans[i] + " ");
        }
    }
}