import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int F, S, G, U, D;
    static int[] building;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        if(S == G) {
            System.out.println(0);
            System.exit(0);
        }

        Queue<Integer> queue = new LinkedList<>();
        building = new int[F+1];

        queue.offer(S);
        building[S] = 1;
        boolean isArrived = false;
        int current, floor, count = 1;
        loop:
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int s = 0; s < size; s++) {
                current = queue.poll();
    
                floor = current + U;
                if(floor <= F && building[floor] == 0) {
                    if(floor == G) {
                        isArrived = true;
                        break loop;
                    }

                    building[floor] = 1;
                    queue.offer(floor);
                }
    
                floor = current - D;
                if(0 < floor && building[floor] == 0) {
                    if(floor == G) {
                        isArrived = true;
                        break loop;
                    }

                    building[floor] = 1;
                    queue.offer(floor);
                }
            }

            count++;
        }


        System.out.println(isArrived ? count : "use the stairs");
    }
}