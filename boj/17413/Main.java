import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<String>();
        StringBuffer sb = new StringBuffer();

        boolean isTag = false;
        for(String s : br.readLine().split("")) {
            if(s.equals("<") || s.equals(" ")) {
                if(s.equals("<")) {
                    isTag = true;
                }

                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }

            } else if(s.equals(">")) {
                isTag = false;
            } else {
                if(!isTag) {
                    stack.push(s);
                    continue;
                }
            }

            sb.append(s);
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }
}
