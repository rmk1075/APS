class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int left = 0, right = numbers.length - 1;
        while(left < right) {
            int sum = numbers[left] + numbers[right];
            if(sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            } else if(sum < target) left++;
            else right--;
        }

        return result;
    }
}

// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         int N = numbers.length;
//         int[] dp = new int[N];
//         for(int i = 1; i < N; i++) {
//             dp[i] = numbers[0] + numbers[i];
//             if(dp[i] == target) return new int[]{1, i + 1};
//         }

//         int[] result = new int[2];
//         loop: for(int i = 1; i < N; i++) {
//             int diff = numbers[i] - numbers[i - 1];
//             for(int j = i + 1; j < N; j++) {
//                 dp[j] += diff;
//                 if(dp[j] == target) {
//                     result[0] = i + 1;
//                     result[1] = j + 1;
//                     break loop;
//                 }
//             }
//         }

//         return result;
//     }
// }