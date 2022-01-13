import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String removeDuplicateLetters(String s) {
        int N = s.length();
        char[] chs = s.toCharArray();
        int[] counts = new int[26];
        for(char ch : chs) counts[ch - 'a']++;

        boolean[] contained = new boolean[26];
        Deque<Character> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            char ch = chs[i];
            counts[ch - 'a']--;
            if(contained[ch - 'a']) continue;
            while(!queue.isEmpty() && ch < queue.peekLast() && counts[queue.peekLast() - 'a'] != 0) contained[queue.pollLast() - 'a'] = false;
            contained[ch - 'a'] = true;
            queue.offer(ch);
        }

        StringBuffer result = new StringBuffer();
        while(!queue.isEmpty()) result.append(queue.poll());
        return result.toString();
    }
}