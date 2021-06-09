class Solution {
    public int findMin(int[] nums) {
        int N = nums.length;
        if(nums[0] < nums[N - 1]) return nums[0];
        for(int i = 1; i < N - 1; i++) {
            if(nums[i] < nums[i - 1]) return nums[i];
        }
        return nums[N - 1];
    }
}