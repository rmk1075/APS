import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        long k = (long) Math.pow(10, 12);
        long[] room_number = new long[200_000];
        for (int i = 0; i < 200_000; i++) {
            // room_number[i] = rand.nextLong() % k;
            room_number[i] = 1;
        }

        // long[] room_number = new long[] { 1000000000000L, 99999999999L };

        Solution sol = new Solution();
        sol.solution(k, room_number);
        // System.out.println(Arrays.toString(sol.solution(k, room_number)));
    }
}
