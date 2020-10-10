import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int[] val = new int[n];
        val[0] = nums[0];
        for(int i = 1; i < n; i++) {
            val[i] = nums[i] + Math.max(0, val[i-1]);
        }

        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            if(ans < val[i]) ans = val[i];
        }

        System.out.println(ans);
    }
}