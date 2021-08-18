import java.util.List;
import java.util.ArrayList;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        int N = nums.length;
        if(N == 0) return result;

        int head = nums[0];
        int tail = nums[0];
        for(int i = 1; i < N; i++) {
            while(i < N && nums[i] - nums[i - 1] == 1) tail = nums[i++];
            result.add(head == tail ? String.valueOf(head) : head + "->" + tail);

            if(i == N) return result;
            head = tail = nums[i];
        }
        result.add(head == tail ? String.valueOf(head) : head + "->" + tail);
        return result;
    }
}