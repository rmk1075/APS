import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] answer = sol.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        System.out.println(Arrays.toString(answer));
    }
}
