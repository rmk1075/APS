import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * mid, min queue, max queue input < mid => min queue => min queue.poll input >
 * mid => max queue => max queue. poll
 */

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> larger = new PriorityQueue<Integer>(Comparator.naturalOrder());
        PriorityQueue<Integer> smaller = new PriorityQueue<Integer>(Comparator.reverseOrder());
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        smaller.add(sc.nextInt());
        System.out.println(smaller.peek());
        for (int i = 1; i < N; i++) {
            int temp = sc.nextInt();
            if (temp <= smaller.peek()) {
                if (larger.size() < smaller.size()) {
                    larger.add(smaller.poll());
                }
                smaller.add(temp);
            } else {
                larger.add(temp);
                if (smaller.size() < larger.size())
                    smaller.add(larger.poll());
            }
            System.out.println(smaller.peek());
        }
        sc.close();
    }
}