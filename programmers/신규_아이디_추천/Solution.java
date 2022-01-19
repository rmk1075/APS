import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String solution(String new_id) {
        char[] chs = new_id.toCharArray();
        Deque<Character> queue = new LinkedList<>();
        for(char ch : chs) queue.offer(ch);
        
        // stage 1
        int size = queue.size();
        while(0 < size--) {
            char ch = Character.toLowerCase(queue.poll());
            queue.offer(ch);
        }

        // stage2
        size = queue.size();
        while(0 < size--) {
            char ch = queue.pollFirst();
            if(Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '-' || ch == '_' || ch == '.') queue.offerLast(ch);
        }

        // stage3
        size = queue.size();
        if(size-- != 0) queue.offerLast(queue.pollFirst());
        while(0 < size--) {
            char ch = queue.pollFirst();
            if(ch == '.' && queue.peekLast() == '.') continue;
            queue.offerLast(ch);
        }

        // stage4
        if(!queue.isEmpty() && queue.peekFirst() == '.') queue.pollFirst();
        if(!queue.isEmpty() && queue.peekLast() == '.') queue.pollLast();

        // stage5
        if(queue.size() == 0) queue.offerLast('a');

        // stage6
        size = queue.size() - 15;
        while(0 < size--) queue.pollLast();
        if(queue.peekLast() == '.') queue.pollLast();

        // stage7
        while(queue.size() < 3) queue.offerLast(queue.peekLast());

        StringBuffer sb = new StringBuffer();
        while(!queue.isEmpty()) sb.append(queue.pollFirst());
        return sb.toString();
    }
}