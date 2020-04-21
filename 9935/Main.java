import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int len = pattern.length;

        Stack<Character> stack = new Stack<>();
        for(char ch : str) {
            if(ch == pattern[len-1]) {
                int i;
                for(i = len-2; -1 < i; i--) {
                    if(stack.isEmpty() || stack.peek() != pattern[i]) break;
                    stack.pop();
                }

                if(i != -1) {
                    i++;
                    for(; i < len; i++) stack.push(pattern[i]);
                }
            } else stack.push(ch);
        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) sb.append(stack.pop());
            System.out.println(sb.reverse());
        }
    }
}