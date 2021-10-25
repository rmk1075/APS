import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        char[] patterns = pattern.toCharArray();
        String[] strs = s.split(" ");
        if(patterns.length != strs.length) return false;
        for(int i = 0; i < patterns.length; i++) {
            char ch = patterns[i];
            if(map.containsKey(ch)) {
                if(!map.get(ch).equals(strs[i])) return false;
            } else {
                if(map.containsValue(strs[i])) return false;
                map.put(ch, strs[i]);
            }
        }
        return true;
    }
}