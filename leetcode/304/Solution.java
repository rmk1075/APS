class NumMatrix {
    int M, N;
    int[][] dp;
    public NumMatrix(int[][] matrix) {
        this.M = matrix.length;
        this.N = matrix[0].length;
        this.dp = matrix.clone();
        for(int i = M - 2; -1 < i; i--) this.dp[i][N - 1] += this.dp[i + 1][N - 1];
        for(int i = N - 2; -1 < i; i--) this.dp[M - 1][i] += this.dp[M - 1][i + 1];
        for(int i = M - 2; -1 < i; i--) {
            for(int j = N - 2; -1 < j; j--) this.dp[i][j] += this.dp[i + 1][j] + this.dp[i][j + 1] - this.dp[i + 1][j + 1];
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return this.dp[row1][col1] - (col2 + 1 < N ? this.dp[row1][col2 + 1] : 0) - (row2 + 1 < M ? this.dp[row2 + 1][col1] : 0) + (row2 + 1 < M && col2 + 1 < N ? this.dp[row2 + 1][col2 + 1] : 0);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */