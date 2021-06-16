import java.util.Arrays;

class Solution {
    public int maximumGap(int[] nums) {
        int N = nums.length;
        if(N < 2) return 0;

        int result = 0;
        Arrays.sort(nums);
        for(int i = 1; i < N; i++) result = Math.max(result, Math.abs(nums[i] - nums[i - 1]));
        return result;
    }
}