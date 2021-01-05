import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(ch);
                continue;
            }

            char peek = stack.peek();
            if ((ch == ')' && peek == '(') || (ch == '}' && peek == '{') || (ch == ']' && peek == '['))
                stack.pop();
            else
                stack.push(ch);
        }

        return stack.isEmpty();
    }
}