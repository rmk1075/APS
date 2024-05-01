class Solution {
  public boolean isPowerOfThree(int n) {
    while (1 < n) {
      if (n % 3 != 0) {
        break;
      }
      n /= 3;
    }
    return n == 1;
  }
}