import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] result = sol.solution(new int[]{5,4,3,2,1});
        System.out.println(Arrays.toString(result));
        
        result = sol.solution(new int[]{1,3,2,5,4,6,3,8});
        System.out.println(Arrays.toString(result));

        result = sol.solution(new int[]{1});
        System.out.println(Arrays.toString(result));

        // result = sol.solution(new int[]{5,4,3,2,1});
        // System.out.println(Arrays.toString(result));

        // result = sol.solution(new int[]{5,4,3,2,1});
        // System.out.println(Arrays.toString(result));

        // result = sol.solution(new int[]{5,4,3,2,1});
        // System.out.println(Arrays.toString(result));
    }
}
