import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[][] board = new int[101][101];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int[] rect : rectangle) {
            for(int i = 0; i < 4; i++) rect[i] *= 2;
            for(int r = rect[0]; r <= rect[2]; r++) {
                for(int c = rect[1]; c <= rect[3]; c++) {
                    // edge
                    if(r == rect[0] || r == rect[2] || c == rect[1] || c == rect[3]) {
                        if(board[r][c] == 0) board[r][c] = -1;
                    } else {
                        board[r][c] = 1;
                    }
                }
            }
        }

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{characterX, characterY});
        board[characterX][characterY] = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(0 < size--) {
                int[] current = queue.poll();
                if(current[0] == itemX && current[1] == itemY) return (answer - 1) / 2 + 1;
                for(int d = 0; d < 4; d++) {
                    int x = current[0] + dx[d];
                    int y = current[1] + dy[d];
                    if(x == 0 || y == 0 || x == 101 || y == 101 || board[x][y] != -1) continue;
                    queue.offer(new int[]{x, y});
                    board[x][y] = 0;
                }
            }
            answer++;
        }
        return answer;
    }
}