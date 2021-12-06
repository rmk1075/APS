class NumArray {
    int len;
    int[] tree;

    public NumArray(int[] nums) {
        len = nums.length;
        tree = new int[len * 2];
        for(int i = len; i < 2 * len; i++) tree[i] = nums[i - len];
        for(int i = len - 1; 0 < i; i--) tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    public void update(int index, int val) {
        index += len;
        tree[index] = val;
        while(0 < index) {
            int left = index;
            int right = index;
            if(index % 2 == 0) right = index + 1;
            else left = index - 1;
            tree[index / 2] = tree[left] + tree[right];
            index /= 2;
        }
    }

    public int sumRange(int left, int right) {
        left += len;
        right += len;
        int sum = 0;
        while(left <= right) {
            if((left % 2) == 1) sum += tree[left++];
            if((right % 2) == 0) sum += tree[right--];
            left /= 2;
            right /= 2;
        }
        return sum;
    }
}
