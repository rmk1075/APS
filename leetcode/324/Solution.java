class Solution {
  private final int INF = 5001;
  public void wiggleSort(int[] nums) {
    int length = nums.length;
    if (length == 1) {
      return ;
    }

    int[] count = new int[INF];
    for (int num : nums) {
      count[num]++;
    }

    int j = 1;
    for (int i = INF - 1; -1 < i; i--) {
      while(0 < count[i]--) {
        nums[j] = i;
        j = length <= j + 2 ? 0 : j + 2;
      }
    }
  }
}