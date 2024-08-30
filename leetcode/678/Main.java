import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()";
        boolean result = solution.checkValidString(s);
        System.out.println(result);
    }
}

class Solution {
    public boolean checkValidString(String s) {
        char[] chs = s.toCharArray();
        Deque<Integer> parenthesis = new LinkedList<>();
        Deque<Integer> star = new LinkedList<>();
        for (int i = 0; i < chs.length; i++) {
            char c = chs[i];
            switch (c) {
                case '(':
                    parenthesis.offer(i);
                    break;
                case ')':
                    if (parenthesis.isEmpty()) {
                        if (star.isEmpty()) {
                            return false;
                        } else {
                            star.pollFirst();
                        }
                    } else {
                        parenthesis.pollLast();
                    }
                    break;
                case '*':
                    star.offer(i);
                    break;
            }
        }

        while (!parenthesis.isEmpty()) {
            int left = parenthesis.peekFirst();
            while (!star.isEmpty() && star.peekFirst() < left) {
                star.pollFirst();
            }

            if (star.isEmpty()) {
                break;
            }

            parenthesis.pollFirst();
            star.pollFirst();
        }

        return parenthesis.isEmpty();
    }
}