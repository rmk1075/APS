import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    Map<String, Integer>[] maps = new Map[11];
    StringBuffer sb = new StringBuffer();
    int cnt = 0;
    public String[] solution(String[] orders, int[] course) {
        for(int c : course) {
            cnt |= 1 << c;
            maps[c] = new HashMap<>();
        }
        for(String order : orders) {
            sb.setLength(0);
            char[] o = order.toCharArray();
            Arrays.sort(o);
            dfs(o, 0, 0);
        }

        List<String> candidates = new LinkedList<>();
        for(int c : course) {
            int maxNum = 2;
            List<String> list = new LinkedList<>();
            Map<String, Integer> map = maps[c];
            for(String key : map.keySet()) {
                if(map.get(key) == maxNum) list.add(key);
                else if(maxNum < map.get(key)) {
                    list.clear();
                    list.add(key);
                    maxNum = map.get(key);
                }
            }
            candidates.addAll(list);
        }

        String[] answer = candidates.toArray(new String[candidates.size()]);
        Arrays.sort(answer);
        return answer;
    }

    public void dfs(char[] order, int idx, int count) {
        if((cnt & (1 << count)) != 0) {
            Map<String, Integer> map = maps[count];
            if(map.containsKey(sb.toString())) map.put(sb.toString(), map.get(sb.toString()) + 1);
            else map.put(sb.toString(), 1);
        }

        for(int i = idx; i < order.length; i++) {
            sb.append(order[i]);
            dfs(order, i + 1, count + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}