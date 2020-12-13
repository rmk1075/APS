import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static PriorityQueue<Integer> smaller = new PriorityQueue<>(Comparator.reverseOrder()),
            larger = new PriorityQueue<>(Comparator.naturalOrder());
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        smaller.add(Integer.parseInt(br.readLine()));
        sb.append(smaller.peek() + "\n");
        for (int i = 1; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
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
            sb.append(smaller.peek() + "\n");
        }

        System.out.println(sb.toString());
    }
}