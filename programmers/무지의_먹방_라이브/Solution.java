import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        int idx = 0, len = food_times.length;
        if (k < len) {
            answer = 1 + (int) k;
            return answer;
        }

        int[][] foods = new int[len][2];
        for (int food : food_times) {
            foods[idx][1] = food;
            foods[idx][0] = ++idx;
        }

        Arrays.sort(foods, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];

                return o1[1] - o2[1];
            }
        });

        idx = 0;
        int cnt = 0;
        long count = 0L;
        while (idx < len) {
            if (k < count + len - idx)
                break;

            count += len - idx;
            cnt++;
            while (idx < len && foods[idx][1] == cnt)
                idx++;
        }

        if (idx == len) {
            answer = -1;
            return answer;
        }

        List<int[]> list = new ArrayList<>();
        for (int i = idx; i < len; i++)
            list.add(foods[i]);

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        answer = list.get((int) (k - count))[0];
        return answer;
    }
}