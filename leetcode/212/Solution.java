import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int m, n, len;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    boolean[][] visited;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for(String word : words) {
            len = word.length();
            if(find(board, word.toCharArray())) result.add(word);
        }
        return result;
    }

    public boolean find(char[][] board, char[] wordToChar) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(wordToChar[0] == board[i][j]) {
                    visited[i][j] = true;
                    boolean flag = dfs(board, i, j, wordToChar, 1);
                    visited[i][j] = false;

                    if(flag) return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int x, int y, char[] wordToChar, int count) {
        if(count == len) return true;

        for(int d = 0; d < 4; d++) {
            int cx = x + dx[d];
            int cy = y + dy[d];
            if(cx < 0 || cy < 0 || cx == m || cy == n || visited[cx][cy] || board[cx][cy] != wordToChar[count]) continue;
            visited[cx][cy] = true;
            boolean flag = dfs(board, cx, cy, wordToChar, count + 1);
            visited[cx][cy] = false;

            if(flag) return true;
        }

        return false;
    }
}