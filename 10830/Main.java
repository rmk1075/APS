import java.util.Scanner;

public class Main {
    static int N;
    static long B;
    static int[][] A;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        B = sc.nextLong();
        A = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                A[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int[][] ans = cal(B);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(String.valueOf(ans[i][j]%1000)+' ');
            }
            System.out.println();
        }
    }

    public static int[][] cal(long n) {
        if(n == 1) return A;

        if(n%2 == 0) {
            int[][] temp_matrix = cal(n/2);
            return matrixCal(temp_matrix, temp_matrix);
        } else {
            int[][] temp_matrix = cal(n/2);
            return matrixCal(temp_matrix, matrixCal(temp_matrix, A));
        }
    }

    public static int[][] matrixCal(int[][] m1, int[][] m2) {
        int[][] result = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    result[i][j] += m1[i][k]*m2[k][j];
                }
                if(result[i][j] != 1000) result[i][j] %= 1000;
            }
        }
        return result;
    }
}