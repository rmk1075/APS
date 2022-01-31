import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for(String inf : info) parseInf("", 0, inf.split(" "));
        for(String key : map.keySet()) Collections.sort(map.get(key));

        int index = 0;
        for(String q : query) {
            String parsedQuery = q.replace(" and ", "");
            String[] splited = parsedQuery.split(" ");
            answer[index++] = binarySearch(splited[0], Integer.parseInt(splited[1]));
        }
        return answer;
    }

    public void parseInf(String key, int depth, String[] infs) {
        if (depth == 4) {
            int score = Integer.parseInt(infs[4]);
            if (map.containsKey(key)) map.get(key).add(score);
            else {
                List<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(key, list);
            };
            return;
        }

        parseInf(key + "-", depth + 1, infs);
        parseInf(key + infs[depth], depth + 1, infs);
    }

    public int binarySearch(String query, int score) {
        if(!map.containsKey(query)) return 0;
        List<Integer> list = map.get(query);
        int start = 0;
        int end = list.size()-1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(score > list.get(mid)) start = mid + 1;
            else end = mid - 1;
        }
        return list.size() - start;
    }
}