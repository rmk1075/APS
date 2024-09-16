class Solution {
    private int minCount = Integer.MAX_VALUE;
    private int R;
    private int C;
    private int[][] target;

    public int solution(int[][] beginning, int[][] target) {
        init(target);
        dfs(0, 0, beginning, target);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private void init(int[][] target) {
        this.R = target.length;
        this.C = target[0].length;
        this.target = target;
    }

    private void dfs(int index, int count, int[][] board, int[][] target) {
        if (isEqual(board)) {
            minCount = count;
            return;
        }

        if (R + C <= index || minCount <= count) {
            return;
        }
        
        dfs(index + 1, count, board, target);
        reverseLine(index, board);
        dfs(index + 1, count + 1, board, target);
        reverseLine(index, board);
    }

    private boolean isEqual(int[][] board) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] reverseLine(int index, int[][] board) {
        if (index < R) {
            return reverseRow(index, board);
        } else {
            return reverseColumn(index - R, board);
        }
    }

    private int[][] reverseRow(int index, int[][] board) {
        for (int i = 0; i < C; i++) {
            board[index][i] = reverse(board[index][i]);
        }
        return board;
    }

    private int[][] reverseColumn(int index, int[][] board) {
        for (int i = 0; i < R; i++) {
            board[i][index] = reverse(board[i][index]);
        }
        return board;
    }

    private int reverse(int value) {
        return value == 0 ? 1 : 0;
    }
}
