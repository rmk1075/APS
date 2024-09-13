import java.util.LinkedList;
import java.util.List;

class Solution {
    public int solution(int[][] scores) {
        int sum = scores[0][0] + scores[0][1];
        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < scores.length; i++) {
            int[] score = scores[i];
            list.add(new int[] { i, score[0], score[1] });
        }

        list.sort((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[2], o2[2]);
            }
            return Integer.compare(o2[1], o1[1]);
        });

        int count = 1;
        int max = -1;
        for (int[] score : list) {
            if (score[2] < max) {
                if (score[0] == 0) {
                    return -1;
                }
                continue;
            }

            max = score[2];
            if (sum < score[1] + score[2]) {
                count++;
            }
        }
        return count;
    }
}
