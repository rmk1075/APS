class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;
        return dfs(matrix, M, N, M - 1, 0, target);
    }

    public boolean dfs(int[][] matrix, int M, int N, int r, int c, int target) {
        if(r < 0 || r == M || c < 0 || c == N) return false;
        if(matrix[r][c] == target) return true;
        if(matrix[r][c] < target) return dfs(matrix, M, N, r, c + 1, target);
        return dfs(matrix, M, N, r - 1, c, target);
    }
}