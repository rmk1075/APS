import java.util.HashMap;
import java.util.Map;;

class Solution {
    Map<Long, Long> map = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        int idx = 0;
        for (long num : room_number) {
            if (map.get(num) == null) {
                map.put(num, num + 1);
                answer[idx++] = num;
            } else {
                long temp = find(num);
                answer[idx++] = temp;
            }
        }

        return answer;
    }

    public long find(long n) {
        if (map.get(n) == null) {
            map.put(n, n + 1);
            return n;
        }

        long temp = find(map.get(n));
        map.put(n, temp);
        return temp;
    }
}