import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static Deque<Integer>[] gears = new Deque[4];
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 4; i++) {
            gears[i] = new LinkedList<Integer>();

            String[] line = br.readLine().split("");
            for(int j = 0; j < 8; j++) {
                gears[i].offer(Integer.parseInt(line[j]));
            }
        }

        int currentGear, direction;
        K = Integer.parseInt(br.readLine());
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            currentGear = Integer.parseInt(st.nextToken()) - 1;
            direction = Integer.parseInt(st.nextToken());

            // left
            left(currentGear, direction);

            if (direction == -1)
                gears[currentGear].offerFirst(gears[currentGear].pollLast());
            else if (direction == 1)
                gears[currentGear].offerLast(gears[currentGear].pollFirst());


            // right
            right(currentGear, direction);

        }

        // calculate the sum of point
        int sum = 0;
        for(int i = 0; i < 4; i++) {
            if(gears[i].peekFirst() == 1) sum += Math.pow(2, i);
        }

        System.out.println(sum);
    }

    public static void left(int current, int direction) {

        if(current != 0) {
            if (gears[current - 1].toArray()[2] != gears[current].toArray()[6])
                left(current - 1, -1 * direction);
        }

        if (direction == 1)
            gears[current].offerFirst(gears[current].pollLast());
        else if (direction == -1)
            gears[current].offerLast(gears[current].pollFirst());
    }

    public static void right(int current, int direction) {

        if(current != 3) {
            if (gears[current].toArray()[2] != gears[current + 1].toArray()[6])
                right(current + 1, -1 * direction);
        }
        
        if (direction == 1)
            gears[current].offerFirst(gears[current].pollLast());
        else if (direction == -1)
            gears[current].offerLast(gears[current].pollFirst());
    }
}