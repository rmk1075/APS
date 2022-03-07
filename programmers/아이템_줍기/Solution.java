import java.util.LinkedList;
import java.util.Queue;

/**
 * overview
 * 
 * 도형의 경계선을 따라서 목적지에 도달해야 한다.
 * 최단거리를 찾아야 하는데, 경계선을 따라서 갈 수 있는 경로는 두가지이다.
 * bfs 방식으로 두 경로 중 더 빨리 도착하는 경로를 반환한다.
 * 
 * description
 * 
 * 경로를 찾기 위해서는 도형의 경계선과 나머지 부분을 구분해야 한다.
 * 도형의 경계는 -1, 도형의 바깥은 0, 도형의 안쪽 부분은 1로 표현하도록 한다.
 * 경로를 따라갈 때 좌표간의 간격이 1인 경우에는 두 좌표가 연결되어 있는지 아니면 경계에서 바라보고 있는지 구분이 되지 않는다.
 * 그렇기 때문에 정확이 경계선을 표현하기 위해서 모든 좌표를 두배로 계산하여 1 ~ 100 까지의 좌표로 표현하도록 한다.
 * 이렇게 구분된 경계를 따라서 최단 거리를 구한 뒤, 구해진 거리에서 최초의 1칸을 빼고 나머지는 다시 2로 나누어서 (answer - 1) / 2 + 1 로 결과를 반환한다.
 */
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