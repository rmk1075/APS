import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
    Solution solution = new Solution();
    int[] answer = solution.solution(edges);
    System.out.println(Arrays.toString(answer));
  }
}
