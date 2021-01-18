class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        int i = 1, j = 1, past = nums[0];
        for (; j < nums.length; j++) {
            if (past == nums[j])
                continue;
            nums[i++] = nums[j];
            past = nums[j];
        }

        return i;
    }
}