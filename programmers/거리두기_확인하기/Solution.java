import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[] visited = new int[5];
    Queue<int[]> queue = new LinkedList<>();
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i = 0; i < 5; i++) answer[i] = checkPlace(places[i]);
        return answer;
    }

    public int checkPlace(String[] place) {
        char[][] board = new char[5][];
        for(int i = 0; i < 5; i++) board[i] = place[i].toCharArray();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(board[i][j] == 'P') {
                    if(bfs(board, i, j)) return 0;
                }
            }
        }
        return 1;
    }

    public boolean bfs(char[][] board, int x, int y) {
        queue.clear();
        Arrays.fill(visited, 0);

        queue.offer(new int[]{x, y});
        visited[x] |= 1 << y;

        int dist = 0;
        while(!queue.isEmpty() && dist++ < 2) {
            int[] current = queue.poll();
            for(int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if(nx < 0 || ny < 0 || nx == 5 || ny == 5 || board[nx][ny] == 'X' || (visited[nx] & (1 << ny)) != 0) continue;
                if(board[nx][ny] == 'P') return true;
                queue.offer(new int[]{nx, ny});
                visited[nx] |= 1 << ny;
            }
        }

        return false;
    }
}