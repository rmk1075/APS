import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] users = new int[][]{{40, 10000}, {25, 10000}};
    int[] emoticons = new int[]{7000, 9000};
    int[] result = solution.solution(users, emoticons);
    System.out.println(Arrays.toString(result));
  }
}
