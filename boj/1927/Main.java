import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for(int i = 0; i < N; i++) {
            int temp = sc.nextInt();
            if(temp == 0) {
                if(queue.peek() == null) System.out.println(0);
                else System.out.println(queue.poll());
            } else {
                queue.add(temp);
            }
        }
        sc.close();
    }
}