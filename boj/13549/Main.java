import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, K;
    static int[] visited = new int[100001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sc.close();

        if(N == K) {
            System.out.println(0);
            System.exit(0);
        }

        if(K < N) {
            System.out.println(N - K);
            System.exit(0);
        }

        for(int i = 0; i < visited.length; i++) {
            visited[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N] = 0;

        int current;
        while(!queue.isEmpty()) {
            current = queue.poll();

            if(0 <= current-1 && visited[current]+1 < visited[current-1]) {
                visited[current-1] = visited[current]+1;
                queue.offer(current-1);
            }

            if(K < current) continue;

            if(current+1 <= K && visited[current]+1 < visited[current+1]) {
                visited[current+1] = visited[current]+1;
                queue.offer(current+1);
            }

            if(current != 0) {
                int temp = current;
                while(temp*2 < 100001) {
                    temp *= 2;
                    if(visited[current] < visited[temp]) {
                        visited[temp] = visited[current];
                        queue.offer(temp);
                    }

                    if(K < temp) break;
                }
            }
        }

        System.out.println(visited[K]);
    }
}