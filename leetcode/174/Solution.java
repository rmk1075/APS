class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int M = dungeon.length, N = dungeon[0].length;
        int[][] dp = new int[M][N];
        dp[M - 1][N - 1] = Math.max(0 - dungeon[M - 1][N - 1] + 1, 1);
        for(int i = M - 2; -1 < i; i--) dp[i][N - 1] = Math.max(dp[i + 1][N - 1] - dungeon[i][N - 1], 1);
        for(int i = N - 2; -1 < i; i--) dp[M - 1][i] = Math.max(dp[M - 1][i + 1] - dungeon[M - 1][i], 1);
        for(int i = M - 2; -1 < i; i--) {
            for(int j = N - 2; -1 < j; j--) dp[i][j] = Math.min(Math.max(dp[i][j + 1] - dungeon[i][j], 1), Math.max(dp[i + 1][j] - dungeon[i][j], 1));
        }

        return dp[0][0];
    }
}