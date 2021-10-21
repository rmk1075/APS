class Solution {
    public void moveZeroes(int[] nums) {
        int num = 0;
        int zero = 0;
        while(num < nums.length) {
            if(nums[num] == 0) num++;
            else {
                int temp = nums[zero];
                nums[zero] = nums[num];
                nums[num] = temp;
                num++;
                zero++;
            }
        }
    }
}