class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0)
            return 0;

        int cnt = (nums[0] == val ? 1 : 0), n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] == val) {
                cnt++;
                continue;
            }

            nums[i - cnt] = nums[i];
        }

        return n - cnt;
    }
}