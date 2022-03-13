import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] a) {
        int N = a.length;
        if(N == 1) return 0;
        Map<Integer, int[]> dp = new HashMap<>(); // int[] {last index, count}
        for(int i = 0; i < N - 1; i++) {
            int x = a[i];
            int y = a[i + 1];
            if(x == y) continue;
            if(dp.containsKey(x)) {
                int[] value = dp.get(x);
                if(1 < i - value[0]) dp.put(x, new int[]{i, value[1] + 1});
            } else dp.put(x, new int[]{i, 1});
            if(dp.containsKey(y)) {
                int[] value = dp.get(y);
                if(1 < i - value[0]) dp.put(y, new int[]{i, value[1] + 1});
            } else dp.put(y, new int[]{i, 1});
        }
        int answer = 0;
        for(int i = 0; i < 2; i++) {
            for(int key : dp.keySet()) {
                answer = Math.max(answer, dp.get(key)[1]);
            }
        }
        return answer * 2;
    }
}