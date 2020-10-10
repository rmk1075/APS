import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int N, height;
    static long count;
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            height = Integer.parseInt(br.readLine());
            if(stack.isEmpty()) stack.push(height);
            else {
                while(!stack.isEmpty() && stack.peek() <= height) stack.pop();
                if(stack.isEmpty() || height < stack.peek()) stack.push(height);
            }
            count += stack.size() - 1;
        }
        System.out.println(count);
    }    
}