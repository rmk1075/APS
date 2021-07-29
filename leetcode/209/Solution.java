class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int N = nums.length;
        int left = 0, right = 1, sum = nums[0], minLen = 100001;
        while(left < right) {
            if(right == N) break;
            if(sum < target) {
                sum += nums[right];
                right++;
            } else {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left];
                left++;
            }
        }

        while(left < right && target <= sum) {
            minLen = Math.min(minLen, right - left);
            sum -= nums[left];
            left++;
        }

        return minLen == 100001 ? 0 : minLen;
    }
}