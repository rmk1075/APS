import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int temp = 0;
        int sign = 1;
        
        char[] c = s.toCharArray();
        for(char ch : c) {
            if(Character.isDigit(ch)) temp = temp * 10 + (ch - '0');
            else {
                switch(ch) {
                    case '+':
                        result += sign * temp;
                        temp = 0;
                        sign = 1;
                        break;
                    case '-':
                        result += sign * temp;
                        temp = 0;
                        sign = -1;
                        break;
                    case '(':
                        stack.add(result);
                        result = 0;

                        stack.add(sign);
                        sign = 1;
                        break;
                    case ')':
                        result += sign * temp;
                        result = stack.pop() * result + stack.pop();
                        temp = 0;
                        break;
                }
            }
        }

        if(temp != 0) result += sign * temp;
        return result;
    }
}