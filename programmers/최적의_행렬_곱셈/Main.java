package programmers.최적의_행렬_곱셈;

public class Main {
    public static void main(String[] args) {
        int[][] matrix_sizes = new int[][] {{5,3},{3,10},{10,6}};
        Solution solution = new Solution();
        int result = solution.solution(matrix_sizes);
        System.out.println(result);
        assert result == 270;
    }
}

class Solution {
    public int solution(int[][] matrix_sizes) {
        int[][] dp = initDp(matrix_sizes);
        cal(dp, matrix_sizes);
        return dp[0][matrix_sizes.length - 1];
    }

    private int[][] initDp(int[][] matrix_sizes) {
        int len = matrix_sizes.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }
        return dp;
    }

    private void cal(int[][] dp, int[][] matrix_sizes) {
        int len = dp.length;
        for (int i = 1; i < len; i++) {
            eachColumn(dp, i, matrix_sizes);
        }
    }

    private void eachColumn(int[][] dp, int col, int[][] matrix_sizes) {
        for (int row = col; 0 < row; row--) {
            eachElement(dp, row, col, matrix_sizes);
        }
    }

    private void eachElement(int[][] dp, int from, int to, int[][] matrix_sizes) {
        int cur = dp[from][to];
        for (int from2 = from - 1; -1 < from2; from2--) {
            dp[from2][to] = Math.min(dp[from2][to], dp[from2][from - 1] + cur + (matrix_sizes[from2][0] * matrix_sizes[from][0] * matrix_sizes[to][1]));
        }
    }
}