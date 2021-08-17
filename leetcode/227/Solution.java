import java.util.Stack;

class Solution {
    public int calculate(String s) {
        int result = 0;
        int past = 0;
        int current = 0;
        char operator = '+';
        
        char[] c = s.toCharArray();
        int N = c.length;
        for(int i = 0; i < N; i++) {
            char ch = c[i];
            if(Character.isDigit(ch)) current = current * 10 + (ch - '0');
            if((!Character.isDigit(ch) && ch != ' ') || i == N - 1) {
                switch(operator) {
                    case '+':
                        result += past;
                        past = current;
                        break;
                    case '-':
                        result += past;
                        past = -current;
                        break;
                    case '*':
                        past *= current;
                        break;
                    case '/':
                        past /= current;
                        break;
                }
                current = 0;
                operator = ch;
            }
        }
        
        result += past;
        return result;
    }
}