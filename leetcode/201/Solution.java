class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while(left < right) right &= (right - 1);
        return right;
    }
}

// class Solution {
//     public int rangeBitwiseAnd(int left, int right) {
//         int result = left;
//         long i = left;
//         for(i = i + 1; i <= right; i++) {
//             result &= i;
//             if(result == 0) break;
//         }
//         return result;
//     }
// }