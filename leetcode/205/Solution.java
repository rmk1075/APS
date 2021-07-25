import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < sa.length; i++) {
            char sc = sa[i];
            char tc = ta[i];
            if(map.containsKey(sc)) {
                if(map.get(sc) != tc) return false;
            } else {
                if(map.containsValue(tc)) return false;
                map.put(sc, tc);
            }
        }

        return true;
    }
}