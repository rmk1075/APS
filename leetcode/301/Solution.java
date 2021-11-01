import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new LinkedList<>();        
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        while(!queue.isEmpty()) {
            boolean isValid = false;
            int size = queue.size();
            while(0 < size--) {
                StringBuffer current = new StringBuffer().append(queue.poll());
                if(valid(current)) {
                    result.add(current.toString());
                    isValid = true;
                    continue;
                }

                for(int i = 0; i < current.length(); i++) {
                    if(current.charAt(i) != '(' && current.charAt(i) != ')') continue;
                    char temp = current.charAt(i);
                    current.deleteCharAt(i);
                    if(!set.contains(current.toString())) {
                        set.add(current.toString());
                        queue.offer(current.toString());
                    }
                    current.insert(i, temp);
                }
            }
            if(isValid) break;
        }
        return result;
    }

    private boolean valid(StringBuffer sb) {
        char[] arr = sb.toString().toCharArray();
        int cnt = 0;
        for(char ch : arr) {
            if(ch == '(') cnt++;
            else if(ch == ')') cnt--;
            if(cnt < 0) return false;
        }
        return cnt == 0;
    }
}