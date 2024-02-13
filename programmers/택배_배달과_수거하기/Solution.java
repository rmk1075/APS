class Solution {
  public long solution(int cap, int n, int[] deliveries, int[] pickups) {
      long answer = 0;
      int delivable = 0;
      int pickable = 0;
      for (int i = n - 1; -1 < i; i--) {
          delivable -= deliveries[i];
          pickable -= pickups[i];

          int count = 0;
          while (delivable < 0 || pickable < 0) {
              delivable += cap;
              pickable += cap;
              count++;
          }

          answer += count * (i + 1) * 2;
      }
      return answer;
  }
}