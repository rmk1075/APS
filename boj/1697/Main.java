import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int N, K, time;
    static int[] visited = new int[100001];
    static Queue<Integer> q;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        sc.close();

        q = new LinkedList<Integer>();
        q.add(N);

        time = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int current = q.poll();

                if(current == K) {
                    System.out.println(time);
                    System.exit(0);
                }

                visited[current] = 1;
                if(current == 0) {
                    q.add(current+1);
                    continue;
                }

                if(0 < current*2 && current*2 < 100001 && visited[current*2] == 0) q.add(current*2);

                if(current+1 < 100001 && visited[current+1] == 0) q.add(current+1);

                if(0 <= current-1 && visited[current-1] == 0) q.add(current-1);
            }

            time++;
        }
    }
}