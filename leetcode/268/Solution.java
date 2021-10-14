class Solution {
    public int missingNumber(int[] nums) {
        int result = 0;
        int N = nums.length;
        for(int i = 0; i < N; i++) result ^= nums[i] ^ i;
        return result ^ N;
    }
}

// class Solution {
//     public int missingNumber(int[] nums) {
//         int sum = 0;
//         for(int num : nums) sum += num;

//         int total = 0;
//         for(int i = 0; i <= nums.length; i++) total += i;

//         return total - sum;
//     }
// }