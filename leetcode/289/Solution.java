class Solution {
    public void gameOfLife(int[][] board) {
        int N = board.length;
        int M = board[0].length;
        int[][] temp = new int[N][M];

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int live = 0;
                for(int d = 0; d < 8; d++) {
                    int x = i + dx[d];
                    int y = j + dy[d];
                    if(x < 0 || y < 0 || x == N || y == M) continue;
                    if(board[x][y] == 1) live++;
                }

                if(board[i][j] == 0) {
                    if(live == 3) temp[i][j] = 1;
                } else {
                    if(live < 2) temp[i][j] = 0;
                    else if(live < 4) temp[i][j] = 1;
                    else temp[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) board[i][j] = temp[i][j];
        }
    }
}