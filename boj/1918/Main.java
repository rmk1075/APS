import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(char ch : br.readLine().toCharArray()) {
            if('A' <= ch && ch <= 'Z') {
                sb.append(ch);
                continue;
            }

            if(ch == ')') {
                while(stack.peek() != '(') sb.append(stack.pop());
                stack.pop();
                continue;
            }

            if(ch == '(') {
                stack.push(ch);
                continue;
            }

            while(!stack.isEmpty() && weight(ch) <= weight(stack.peek())) sb.append(stack.pop());
            stack.push(ch);
        }

        while(!stack.isEmpty()) sb.append(stack.pop());
        System.out.println(sb);
    }

    public static int weight(char ch) {
        switch (ch) {
            case '*': case '/':
                return 2;
            case '+': case '-':
                return 1;
            default:
                return 0;
        }
    }
}