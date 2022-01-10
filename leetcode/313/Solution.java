class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int length = primes.length;
        int[] nums = new int[n];
        int[] idx = new int[length];
        nums[0] = 1;

        for(int i = 1; i < n; i++) {
            int temp = Integer.MAX_VALUE;
            for(int j = 0; j < length; j++) {
                if(primes[j] * nums[idx[j]] == nums[i - 1]) idx[j]++;
                temp = Math.min(temp, primes[j] * nums[idx[j]]);
            }
            nums[i] = temp;
        }
        return nums[n - 1];
    }
}