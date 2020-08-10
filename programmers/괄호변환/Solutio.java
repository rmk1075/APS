import java.util.Stack;

class Solution {
    public String solution(String p) {

        if(p.equals("")) return p;

        char[] str = p.toCharArray();
        int N = p.length(), l = 0, r = 0;
        for(int i = 0; i < N; i++) {
            if(str[i] == '(') l++;
            else r++;

            if(l == r) {
                String u = p.substring(0, i + 1), v = p.substring(i + 1);
                if(clear(u)) {
                    return u + solution(v);
                } else {
                    String answer = "(" + solution(v) + ")";
                    answer += reverse(u.substring(1, u.length() - 1));
                    return answer;
                }
            }
        }

        return "";
    }

    public static String reverse(String p) {
        String str = "";
        for(char ch : p.toCharArray()) {
            str += (ch == '(') ? ')' : '(';
        }

        return str;
    }

    public static boolean clear(String p) {
        Stack<Character> stack = new Stack<>();

        for(char ch : p.toCharArray()) {
            if(ch == '(') {
                stack.push(ch);
            } else {
                if(stack.isEmpty()) return false;
                else stack.pop();
            }
        }

        return true;
    }
}