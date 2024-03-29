class Solution {
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] result = new int[N];
        result[0] = 1;

        // left to right
        for(int i = 1; i < N; i++) result[i] = nums[i - 1] * result[i - 1];

        // right to left
        int temp = 1;
        for(int i = N - 2; -1 < i; i--) {
            temp *= nums[i + 1];
            result[i] *= temp;
        }

        return result;
    }
}

// class Solution {
//     public int[] productExceptSelf(int[] nums) {
//         int N = nums.length;
//         int[] result = new int[N];

//         int product = 1;
//         int zeroCnt = 0;
//         for(int num : nums) {
//             if(num == 0) {
//                 zeroCnt++;
//                 if(1 < zeroCnt) return result;
//             } else {
//                 product *= num;
//             }
//         }

//         if(zeroCnt == 0) {
//             for(int i = 0; i < N; i++) {
//                 result[i] = product / nums[i];
//             }
//         } else {
//             for(int i = 0; i < N; i++) {
//                 if(nums[i] == 0) {
//                     result[i] = product;
//                     break;
//                 }
//             }
//         }

//         return result;
//     }
// }