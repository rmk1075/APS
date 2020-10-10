import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();

        char[] ch1 = str1.toCharArray(), ch2 = str2.toCharArray();
        int n1 = ch1.length, n2 = ch2.length;

        for(int i = 0; i < n1 - 1; i++) {
            ch1[i] = Character.toUpperCase(ch1[i]);
            ch1[i + 1] = Character.toUpperCase(ch1[i + 1]);

            if(ch1[i] < 'A' || 'Z' < ch1[i] || ch1[i + 1] < 'A' || 'Z' < ch1[i + 1]) continue;

            String str = String.valueOf(ch1[i]);
            str += ch1[i + 1];
            if(map1.containsKey(str)) {
                map1.put(str, map1.get(str) + 1);
            } else {
                map1.put(str, 1);
            }
        }

        for(int i = 0; i < n2 - 1; i++) {
            ch2[i] = Character.toUpperCase(ch2[i]);
            ch2[i + 1] = Character.toUpperCase(ch2[i + 1]);

            if(ch2[i] < 'A' || 'Z' < ch2[i] || ch2[i + 1] < 'A' || 'Z' < ch2[i + 1]) continue;
            
            String str = String.valueOf(ch2[i]);
            str += ch2[i + 1];
            if(map2.containsKey(str)) {
                map2.put(str, map2.get(str) + 1);
            } else {
                map2.put(str, 1);
            }
        }

        // intersection, union
        double intersection = 0, union = 0;
        for(String key : map1.keySet()) {
            if(map2.containsKey(key)) {
                int a = map1.get(key), b = map2.get(key);
                intersection += Math.min(a, b);
                union += Math.max(a , b);

                map2.remove(key);
            } else {
                union += map1.get(key);
            }
        }

        for(int val : map2.values()) {
            union += val;
        }

        if(union == 0) return 65536;
        answer = (int)(intersection / union * 65536);
        return answer;
    }
}