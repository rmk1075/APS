import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][C];
        for(int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int[][][] cntBoard = new int[4][R][C];
        // 0
        for(int i = 0; i < C; i++) {
            count(R, C, board, cntBoard[0], 1, 1, 0, i);
        }

        for(int i = 1; i < R; i++) {
            count(R, C, board, cntBoard[0], 1, 1, i, 0);
        }

        // 1
        for(int i = 0; i < C; i++) {
            count(R, C, board, cntBoard[1], 1, -1, 0, i);
        }

        for(int i = 1; i < R; i++) {
            count(R, C, board, cntBoard[1], 1, -1, i, C - 1);
        }

        // 2
        for(int i = 0; i < C; i++) {
            count(R, C, board, cntBoard[2], -1, -1, R - 1, i);
        }

        for(int i = 0; i < R - 1; i++) {
            count(R, C, board, cntBoard[2], -1, -1, i, C - 1);
        }

        // 3
        for(int i = 0; i < C; i++) {
            count(R, C, board, cntBoard[3], -1, 1, R - 1, i);
        }

        for(int i = 0; i < R - 1; i++) {
            count(R, C, board, cntBoard[3], -1, 1, i, 0);
        }

        // calculate
        int maxSize = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(board[i][j] == '0')
                    continue;

                int distance = Math.min(cntBoard[2][i][j], cntBoard[3][i][j]);
                if(distance + 1 <= maxSize) continue;

                for(int d = distance - 1; -1 < d; d--) {
                    if(-1 < j - d && i + d + d < R && j + d < C && d <= cntBoard[3][i + d][j + d] && d <= cntBoard[0][i + d + d][j] && d <= cntBoard[1][i + d][j - d]) {
                        maxSize = Math.max(maxSize, d + 1);
                        break;
                    }
                }
            }
        }

        System.out.println(maxSize);
    }

    public static void count(int R, int C, char[][] board, int[][] cntBoard, int dx, int dy, int x, int y) {
        int cnt = 0;
        while(-1 < x && -1 < y && x < R && y < C) {
            if(board[x][y] == '0') {
                cnt = 0;
            } else {
                cnt += 1;
                cntBoard[x][y] = cnt;
            }

            x += dx;
            y += dy;
        }
    }
}