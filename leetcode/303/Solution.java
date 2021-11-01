class NumArray {
    int[] sums;

    public NumArray(int[] nums) {
        int N = nums.length;
        this.sums = new int[N + 1];
        for(int i = 1; i < N + 1; i++) this.sums[i] = this.sums[i - 1] + nums[i - 1];
    }
    
    public int sumRange(int left, int right) {
        return this.sums[right + 1] - this.sums[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */