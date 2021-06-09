class Solution {
    public int findMin(int[] nums) {
        int N = nums.length;
        if(nums[0] < nums[N - 1]) return nums[0];

        int left = 0, right = N - 1;
        int mid;
        while(left < right) {
            mid = (left + right) / 2;
            if(nums[mid] < nums[right]) {
                right = mid;
            } else if(nums[right] < nums[mid]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        return nums[right];
    }
}