import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, K;
    static int[][] nums;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sc.close();
        nums = new int[K+1][N+1];

        nums[0][0] = 1;
        for(int i = 0; i < K; i++) {
            for(int j = 0; j < N+1; j++) {
                for(int k = 0; k < N+1; k++) {
                    if(nums[i][k] != 0 && j+k < N+1) {
                        nums[i+1][j+k] = (nums[i+1][j+k] + nums[i][k]) % 1_000_000_000;
                    }
                }
            }
        }

        System.out.println(nums[K][N]);
    }
}