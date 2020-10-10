import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> positive = new PriorityQueue<Integer>();
        PriorityQueue<Integer> negative = new PriorityQueue<Integer>();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int i = 0; i < N; i++) {
            int x = sc.nextInt();
            if(x == 0) {
                if(positive.isEmpty() && negative.isEmpty()) {
                    System.out.println(0);
                } else {
                    if(positive.peek() == null || negative.peek() == null) {
                        if(negative.peek() == null) System.out.println(positive.poll());
                        else if(positive.peek() == null) System.out.println(negative.poll()*-1);
                    } else {
                        if(negative.peek() <= positive.peek()) {
                            System.out.println(negative.poll()*-1);
                        } else {
                            System.out.println(positive.poll());
                        }
                    }
                }
            } else {
                if(x < 0) {
                    negative.add(x*-1);
                } else {
                    positive.add(x);
                }
            }
        }
        sc.close();
    }
}