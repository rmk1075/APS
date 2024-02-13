public class Main {
  public static void main(String[] args) {
    Solution sol = new Solution();
    int cap = 4;
    int n = 5;
    int[] deliveries = {1, 0, 3, 1, 2};
    int[] pickups = {0, 3, 0, 4, 0};
    System.out.println(sol.solution(cap, n, deliveries, pickups));
  }
}
