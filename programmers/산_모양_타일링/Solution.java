class Solution {
  private static final int MOD = 10007;
  public int solution(int n, int[] tops) {
      /**
       * dynamic programming
       * -> the number of ways to cover the center inverted triangle
       * 
       * - rightCovered: cover by the shape
       *        _____
       *        \   / \
       *         \ /___\
       * 
       * - orNot: cover by the shape without the above shape
       */
      int rightCovered = 0;
      int orNot = 1;

      for (int top : tops) {
        int temp = (rightCovered + orNot) % MOD;
        if (top == 0) {
          orNot = (rightCovered + orNot * 2) % MOD;
        } else {
          orNot = (rightCovered * 2 + orNot * 3) % MOD;
        }
        rightCovered = temp;
      }
      return (rightCovered + orNot) % MOD;
  }
}