import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    char[][] map;
    boolean[][] visitedMap;
    public String solution(int m, int n, String[] board) {
        String answer = "";

        int num = 0;
        map = new char[m][n];
        visitedMap = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
            for(int j = 0; j < n; j++) {
                if(Character.isAlphabetic(map[i][j])) num++;
            }
        }
        num /= 2;

        int visited = 0, idx = 0;
        int[][] alphabet = new int[num][5]; // {ch, x1, y1, x2, y2}
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(Character.isAlphabetic(map[i][j])) {
                    char ch = map[i][j];
                    if((visited & (1 << ch)) == 0) {
                        visited |= 1 << ch;
                        alphabet[idx][0] = ch;
                        alphabet[idx][1] = i;
                        alphabet[idx++][2] = j;
                        continue;
                    }

                    for(int k = 0; k < idx; k++) {
                        if(alphabet[k][0] != ch) continue;

                        alphabet[k][3] = i;
                        alphabet[k][4] = j;
                        break;
                    }
                }
            }
        }


        visited = 0;
        for(int i = 0; i < num; i++) {
            char alpha = find(m, n, visited, num, alphabet);

            if(alpha == '*') return "IMPOSSIBLE";

            visited |= 1 << alpha;
            
            answer += alpha;
            for(int j = 0; j < num; j++) {
                if(alphabet[j][0] == alpha) {
                    int[] al = alphabet[j];
                    map[al[1]][al[2]] = map[al[3]][al[4]] = '.';
                }
            }
        }

        return answer;
    }

    public char find(int m, int n, int visited, int num, int[][] alphabet) {
        char ch = 'Z' + 1;
        for(int i = 0; i < num; i++) {
            if((visited & (1 << alphabet[i][0])) != 0 || ch < alphabet[i][0]) continue;
            
            for(int j = 0; j < m; j++) Arrays.fill(visitedMap[j], false);
            visitedMap[alphabet[i][1]][alphabet[i][2]] = true;

            int x, y, x1 = alphabet[i][1], y1 = alphabet[i][2], x2 = alphabet[i][3], y2 = alphabet[i][4];
            for(int d = 0; d < 4; d++) {
                x = x1 + dx[d];
                y = y1 + dy[d];
                if((x == x2 && y == y2) || (-1 < x && -1 < y && x != m && y != n && map[x][y] == '.' && dfs(m, n, alphabet[i][1], alphabet[i][2], alphabet[i][3], alphabet[i][4], 0, d))) {
                    ch = (char)alphabet[i][0];
                    break;
                }
            }
        }

        return ch == 'Z' + 1 ? '*' : ch;
    }

    public boolean dfs(int m, int n, int x1, int y1, int x2, int y2, int cnt, int dir) {
        int x, y;
        for(int d = 0; d < 4; d++) {
            x = x1 + dx[d];
            y = y1 + dy[d];
            if(d != dir && cnt == 1) continue;
            if(x == x2 && y == y2) return true;
            if(x < 0 || y < 0 || x == m || y == n || visitedMap[x][y] || map[x][y] != '.') continue;
            
            visitedMap[x][y] = true;
            if(dfs(m, n, x, y, x2, y2, (d == dir ? cnt : cnt + 1), d)) return true;
            visitedMap[x][y] = false;
        }

        return false;
    }
}