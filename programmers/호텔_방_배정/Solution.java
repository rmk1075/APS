import java.util.HashMap;
import java.util.Map;

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        Map<Long, Long> map = new HashMap<>();
        int index = 0;
        for (long number : room_number) {
            if (!map.containsKey(number)) {
                answer[index++] = number;
                map.put(number, find(map, number + 1));
                continue;
            }

            long temp = find(map, number + 1);
            answer[index++] = temp;
            map.put(temp, find(map, temp + 1));
        }

        return answer;
    }

    public long find(Map<Long, Long> map, long number) {
        long result = number;
        while (true) {
            if (map.containsKey(result)) {
                result = map.get(result);
            } else
                break;
        }

        return result;
    }
}