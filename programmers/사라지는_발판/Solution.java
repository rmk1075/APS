/**
 * overview
 * 
 * A 와 B 는 각각의 차례에 자신이 이길 수 있는 최선의 경우의 수를 반환한다.
 * 이기는 경우에는 가장 최단 순서로 이겨야하고, 지는 경우에는 최장 순서로 져야한다.
 * 반환되는 값들을 통해서 자신이 이기는 경우가 있는지 확인하고, 이를 통해 결과를 반환한다.
 * 
 * 
 * description
 * 
 * 각 차례에서 모든 경우의 수에 대한 결과를 반환받는다.
 * recursion 의 결과로는 {지는 대상, 차례} 를 반환한다. 지는 대상은 A는 0, B는 1 을 반환하도록 계산했다.
 * 이기는 경우에는 min, 지는 경우에는 max 값을 계산해야 한다.
 * 각각 turn 과 결과로 받은 지는 대상을 통해 win 과 lose 를 계산한다.
 * win 의 경우가 하나라도 있는 경우는 win 을, 그렇지 않은 경우에는 lose 를 반환한다.
 */
class Solution {
    int R, C;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[] map;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        R = board.length;
        C = board[0].length;
        map = new int[R];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(board[i][j] == 1) map[i] |= 1 << j;
            }
        }

        int[][] locs = new int[][]{aloc, bloc};
        int[] answer = dfs(locs, 0);
        return answer[1];
    }

    public int[] dfs(int[][] locs, int count) {
        int turn = count % 2 == 0 ? 0 : 1;
        if(isLoser(locs[turn])) {
            return new int[]{turn, count};
        }

        int win = Integer.MAX_VALUE;
        int lose = -1;
        int x = locs[turn][0];
        int y = locs[turn][1];
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || nx == R || ny < 0 || ny == C) continue;
            if((map[nx] & (1 << ny)) != 0) {
                locs[turn][0] = nx;
                locs[turn][1] = ny;
                map[x] &= ~(1 << y);

                int[] result = dfs(locs, count + 1);
                // A lose
                if(result[0] == 0) {
                    // A turn
                    if(turn == 0) lose = Math.max(lose, result[1]);
                    // B turn
                    else win = Math.min(win, result[1]);
                // A win
                } else {
                    // A turn
                    if(turn == 0) win = Math.min(win, result[1]);
                    // B turn
                    else lose = Math.max(lose, result[1]);
                }

                locs[turn][0] = x;
                locs[turn][1] = y;
                map[x] |= 1 << y;
            }
        }

        if(win != Integer.MAX_VALUE) return new int[]{(turn + 1) % 2, win};
        else return new int[]{turn, lose};
    }

    public boolean isLoser(int[] loc) {
        if((map[loc[0]] & (1 << loc[1])) == 0) return true;
        for(int d = 0; d < 4; d++) {
            int x = loc[0] + dx[d];
            int y = loc[1] + dy[d];
            if(x < 0 || x == R || y < 0 || y == C) continue;
            if((map[x] & (1 << y)) != 0) return false;
        }
        return true;
    }
}