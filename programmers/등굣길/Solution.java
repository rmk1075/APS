class Solution {
    int div = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] board = new int[m][n];
        for(int[] puddle : puddles) board[puddle[0] - 1][puddle[1] - 1] = -1;

        board[0][0] = 1;
        for(int i = 1; i < n; i++) {
            if(board[0][i] == -1) break;
            board[0][i] = board[0][i - 1];
        }

        for(int i = 1; i < m; i++) {
            if(board[i][0] == -1) break;
            board[i][0] = board[i - 1][0];
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(board[i][j] == -1) continue;
                int top = board[i - 1][j];
                int left = board[i][j - 1];
                board[i][j] = ((top == -1 ? 0 : top % div) + (left == -1 ? 0 : left % div)) % div;
            }
        }

        answer = board[m - 1][n - 1];
        return answer;
    }
}