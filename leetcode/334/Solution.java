// class Solution {
//   private int[] minArr, maxArr;
//   public boolean increasingTriplet(int[] nums) {
//     init(nums);
//     for (int i = 1; i < nums.length - 1; i++) {
//       int num = nums[i];
//       if (minArr[i] < num && num < maxArr[i]) {
//         return true;
//       }
//     }
//     return false;
//   }
  
//   private void init(int[] nums) {
//     int length = nums.length;
//     minArr = new int[length];
//     minArr[0] = nums[0];
//     for (int i = 1; i < length; i++) {
//       minArr[i] = Math.min(minArr[i - 1], nums[i]);
//     }

//     maxArr = new int[length];
//     maxArr[length - 1] = nums[length - 1];
//     for (int i = length - 2; -1 < i; i--) {
//       maxArr[i] = Math.max(maxArr[i + 1], nums[i]);
//     }
//   }
// }

class Solution {
  public boolean increasingTriplet(int[] nums) {
    int numi = Integer.MAX_VALUE;
    int numj = Integer.MAX_VALUE;
    for (int numk : nums) {
      if (numk <= numi) {
        numi = numk;
      } else if (numk <= numj) {
        numj = numk;
      } else {
        return true;
      }
    }
    return false;
  }
}