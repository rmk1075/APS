import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public String removeDuplicateLetters(String s) {
        int N = s.length();
        char[] chs = s.toCharArray();
        int[] counts = new int[26];
        for(char ch : chs) counts[ch - 'a']++;

        boolean[] contained = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            char ch = chs[i];
            counts[ch - 'a']--;
            if(contained[ch - 'a']) continue;
            while(!stack.isEmpty() && ch < stack.peek() && counts[stack.peek() - 'a'] != 0) contained[stack.pop() - 'a'] = false;
            contained[ch - 'a'] = true;
            stack.push(ch);
        }

        StringBuffer result = new StringBuffer();
        while(!stack.isEmpty()) result.insert(0, stack.pop());
        return result.toString();
    }
}