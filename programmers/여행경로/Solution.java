import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    int N;
    public String[] solution(String[][] tickets) {
        String[] answer = {};

        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(String[] ticket : tickets) {
            if(map.containsKey(ticket[0])) map.get(ticket[0]).offer(ticket[1]);
            else {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.offer(ticket[1]);
                map.put(ticket[0], pq);
            }
        }
        
        N = tickets.length;
        List<String> list = new LinkedList<>();
        list.add("ICN");
        dfs(map, "ICN", list, 1);

        answer = list.toArray(new String[list.size()]);
        return answer;
    }

    public boolean dfs(Map<String, PriorityQueue<String>> map, String airport, List<String> list, int index) {
        if(index == N + 1) return true;
        if(!map.containsKey(airport)) return false;

        List<String> airports = new LinkedList<>();
        airports.addAll(map.get(airport));
        for(String next : airports) {
            map.get(airport).remove(next);
            list.add(next);

            if(dfs(map, next, list, index + 1)) return true;

            list.remove(index);
            map.get(airport).offer(next);
        }

        return false;
    }
}