import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new LinkedList<>();
        int N = s.length();
        if(N < 10) return result;

        Map<String, Boolean> map = new HashMap<>();
        for(int i = 0; i < N - 9; i++) {
            String str = s.substring(i, i + 10);
            if(map.containsKey(str)) {
                if(map.get(str)) continue;
                else {
                    result.add(str);
                    map.put(str, true);
                }
            } else map.put(str, false);
        }
        return result;
    }
}