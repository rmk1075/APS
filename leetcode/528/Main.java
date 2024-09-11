import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Solution {

    private int[] w;
    private List<Integer> probabilitiesList;
    private int sum;
    private Random random = new Random();

    public Solution(int[] w) {
        this.w = w;
        this.probabilitiesList = generateProbabilitesList();
    }

    private List<Integer> generateProbabilitesList() {
        List<Integer> result = new ArrayList<>();
        sum = 0;
        for (int num : this.w) {
            sum += num;
            result.add(sum);
        }
        return result;
    }

    public int pickIndex() {
        int index = random.nextInt(sum) + 1;
        int left = 0;
        int right = probabilitiesList.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (probabilitiesList.get(mid) < index) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */