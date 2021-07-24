class Solution {
    public int countPrimes(int n) {
        if(n < 2) return 0;

        int result = n - 2;
        boolean[] nums = new boolean[n];
        nums[0] = nums[1] = true;
        for(int i = 2; i < n / 2 + 1; i++) {
            if(nums[i]) continue;
            int val = i * 2;
            while(val < n) {
                if(!nums[val]) {
                    nums[val] = true;
                    result--;
                }
                val += i;
            }
        }

        return result;
    }
}