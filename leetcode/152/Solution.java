class Solution {
    public int maxProduct(int[] nums) {
        int product = nums[0], minVal = nums[0], maxVal = nums[0];
        int N = nums.length;
        for(int i = 1; i < N; i++) {
            if(nums[i] < 0) {
                int temp = minVal;
                minVal = maxVal;
                maxVal = temp;
            }

            minVal = Math.min(nums[i], nums[i] * minVal);
            maxVal = Math.max(nums[i], nums[i] * maxVal);
            product = Math.max(product, maxVal);
        }
        return product;
    }
}