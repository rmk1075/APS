class Solution {
    public int findPeakElement(int[] nums) {
        int N = nums.length;
        if(N == 1) return 0;

        for(int i = 1; i < N - 1; i++) {
            if(nums[i - 1] < nums[i] && nums[i + 1] < nums[i]) return i;
        }

        if(nums[1] < nums[0]) return 0;
        return N - 1;
    }
}