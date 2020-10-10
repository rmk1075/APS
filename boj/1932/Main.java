import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] triangle = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                triangle[i][j] = sc.nextInt();
            }
        }
        sc.close();

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                if(j == 0) {
                    triangle[i][j] += triangle[i-1][j];
                } else if(j == i) {
                    triangle[i][j] += triangle[i-1][j-1];
                } else {
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            if(ans < triangle[n-1][i]) ans = triangle[n-1][i];
        }
        System.out.println(ans);
    }
}