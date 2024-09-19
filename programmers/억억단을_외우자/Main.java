package programmers.억억단을_외우자;

import java.util.Arrays;

public class Main {
    
}

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] counts = initCount(e);
        int[] maxCounts = initMaxCounts(e, counts);
        return Arrays.stream(starts).map(start -> maxCounts[start]).toArray();
    }

    private int[] initCount(int e) {
        int[] counts = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = 1; i * j <= e; j++) {
                counts[i * j]++;
            }
        }
        return counts;
    }

    private int[] initMaxCounts(int e, int[] counts) {
        int[] maxCounts = new int[e + 1];
        maxCounts[e] = e;
        for (int i = e - 1; 0 < i; i--) {
            if (counts[maxCounts[i + 1]] <= counts[i]) {
                maxCounts[i] = i;                
            } else {
                maxCounts[i] = maxCounts[i + 1];
            }
        }
        return maxCounts;
    }
}
