import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        int n = gems.length, m = 0;
        Map<String, Integer> map = new HashMap<>();
        for(String g : gems) {
            if(map.containsKey(g)) continue;
            map.put(g, m++);
        }

        int cnt = 1, left = 0, right = 0;
        int[] count = new int[m];
        count[map.get(gems[0])]++;

        int minLen = n;
        while(right < n) {
            if(cnt == m) {
                if(right - left < minLen) {
                    minLen = right - left;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                if(--count[map.get(gems[left++])] == 0) cnt--;
            } else {
                if(right == n - 1) break;
                if(++count[map.get(gems[++right])] == 1) cnt++;
            }
        }        

        return answer;
    }
}