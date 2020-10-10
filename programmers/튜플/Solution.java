import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        
        List<String[]> list = new ArrayList<>();
        for(String str : s.split("\\},\\{")) {
            list.add(str.split(","));
        }

        Collections.sort(list, new Comparator<String[]>(){
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        });
        
        int index = 0;
        int[] answer = new int[list.size()];
        Map<String, Integer> map = new HashMap<>(), temp = new HashMap<>();
        for(String[] arr : list) {
            for(String key : map.keySet()) {
                temp.put(key, map.get(key));
            }

            for(String str : arr) {
                if(temp.containsKey(str)) {
                    int value = temp.get(str);
                    if(value == 0) {
                        map.put(str, 1);
                        answer[index++] = Integer.parseInt(str);    
                        break;
                    } else {
                        temp.put(str, value - 1);
                    }

                } else {
                    map.put(str, 1);
                    answer[index++] = Integer.parseInt(str);
                    break;
                }
            }
        }

        return answer;
    }
}