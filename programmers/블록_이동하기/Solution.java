import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    boolean[][][] visited;
    Queue<int[]> queue;
    public int solution(int[][] board) {
        int answer = 0;

        int n = board.length;
        visited = new boolean[n][n][2]; // status: 0(horizontal), 1(vertical)
        visited[0][0][0] = true;

        queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // {x, y, status: 0(horizontal), 1(vertical)}
        
        int size;
        loop: while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                int[] current = queue.poll();
                int x = current[0], y = current[1];

                if(current[2] == 0) {
                    if(x == n - 1 && y == n - 2) break loop;

                    if(0 < x && board[x - 1][y] == 0 && board[x - 1][y + 1] == 0 && !visited[x - 1][y][0]) input(x - 1, y, 0);

                    if(0 < x && board[x - 1][y] == 0 && board[x - 1][y + 1] == 0 && !visited[x - 1][y][1]) input(x - 1, y, 1);

                    if(0 < x && board[x - 1][y] == 0 && board[x - 1][y + 1] == 0 && !visited[x - 1][y + 1][1]) input(x - 1, y + 1, 1);


                    if(0 < y && board[x][y - 1] == 0 && !visited[x][y - 1][0]) input(x, y - 1, 0);

                    if(y + 2 < n && board[x][y + 2] == 0 && !visited[x][y + 1][0]) input(x, y + 1, 0);

                    
                    if(x + 1 < n && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0 && !visited[x + 1][y][0]) input(x + 1, y, 0);

                    if(x + 1 < n && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0 && !visited[x][y][1]) input(x, y, 1);

                    if(x + 1 < n && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0 && !visited[x][y + 1][1]) input(x, y + 1, 1);

                } else {
                    if(x == n - 2 && y == n - 1) break loop;

                    if(0 < x && board[x - 1][y] == 0 && !visited[x - 1][y][1]) input(x - 1, y, 1);

                    
                    if(y + 1 < n && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0 && !visited[x][y][0]) input(x, y, 0);

                    if(y + 1 < n && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0 && !visited[x + 1][y][0]) input(x + 1, y, 0);
                    
                    if(y + 1 < n && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0 && !visited[x][y + 1][1]) input(x, y + 1, 1);

                    
                    if(x + 2 < n && board[x + 2][y] == 0 && !visited[x + 1][y][1]) input(x + 1, y, 1);


                    if(0 < y && board[x][y - 1] == 0 && board[x + 1][y - 1] == 0 && !visited[x][y - 1][0]) input(x, y - 1, 0);

                    if(0 < y && board[x][y - 1] == 0 && board[x + 1][y - 1] == 0 && !visited[x][y - 1][1]) input(x, y - 1, 1);

                    if(0 < y && board[x][y - 1] == 0 && board[x + 1][y - 1] == 0 && !visited[x + 1][y - 1][0]) input(x + 1, y - 1, 0);
                }
            }

            answer++;
        }


        return answer;
    }

    public void input(int x, int y, int d) {
        visited[x][y][d] = true;
        queue.offer(new int[]{x, y, d});
    }
}