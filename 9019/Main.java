import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Value {
    int val;
    ArrayList<Character> commands = new ArrayList<>();

    Value(int val) {
        this.val = val;
    }

    Value(int val, ArrayList<Character> list, char ch) {
        this.val = val;
        this.commands.addAll(list);
        this.commands.add(ch);
    }
}

public class Main {
    static int T, A, B;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            Queue<Value> queue = new LinkedList<Value>();
            queue.add(new Value(A));
            visited[A] = true;
            while(!queue.isEmpty()) {
                Value current = queue.poll();
                int temp;

                // D
                temp = (2 * current.val) % 10000;
                if(temp == B) {
                    current.commands.forEach((ch) -> System.out.print(ch));
                    System.out.println("D");
                    break;
                } else if(!visited[temp]) {
                    visited[temp] = true;
                    queue.add(new Value(temp, current.commands, 'D'));
                }

                // S
                temp = (current.val == 0) ? 9999 : current.val - 1;
                if(temp == B) {
                    current.commands.forEach((ch) -> System.out.print(ch));
                    System.out.println("S");
                    break;
                } else if(!visited[temp]) {
                    visited[temp] = true;
                    queue.add(new Value(temp, current.commands, 'S'));
                }

                // L
                temp = (current.val%1000) * 10 + (current.val/1000);
                if(temp == B) {
                    current.commands.forEach((ch) -> System.out.print(ch));
                    System.out.println("L");
                    break;
                } else if(!visited[temp]) {
                    visited[temp] = true;
                    queue.add(new Value(temp, current.commands, 'L'));
                }

                // R
                temp = (current.val%10)*1000 + (current.val/10);
                if(temp == B) {
                    current.commands.forEach((ch) -> System.out.print(ch));
                    System.out.println("R");
                    break;
                } else if(!visited[temp]) {
                    visited[temp] = true;
                    queue.add(new Value(temp, current.commands, 'R'));
                }
            }
        }
    }
}