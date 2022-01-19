import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        Map<String, Integer> map = new HashMap<>(N);
        for(int i = 0; i < N; i++) map.put(id_list[i], i);
        
        int[] reportCounts = new int[N];
        boolean[][] reportTable = new boolean[N][N];
        for(String rep : report) {
            String[] r = rep.split(" ");
            if(!reportTable[map.get(r[0])][map.get(r[1])]) {
                reportTable[map.get(r[0])][map.get(r[1])] = true;
                reportCounts[map.get(r[1])]++;
            }
        }
        
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
            if(reportCounts[i] < k) continue;
            for(int j = 0; j < N; j++) {
                if(reportTable[j][i]) answer[j]++;
            }
        }
        return answer;
    }
}