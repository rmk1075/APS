import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ball {
    int rx, ry, bx, by;

    Ball(int rx, int ry, int bx, int by) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
    }
}

public class Main {
    static int N, M, count = 1;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] board;
    static boolean[][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;
        for(int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                if(board[i][j] == 'R') {
                    board[i][j] = '.';
                    rx = i;
                    ry = j;
                } else if(board[i][j] == 'B') {
                    board[i][j] = '.';
                    bx = i;
                    by = j;
                }
            }
        }

        Queue<Ball> queue = new LinkedList<>();
        queue.offer(new Ball(rx, ry, bx, by));
        visited[rx][ry][bx][by] = true;

        int size;
        while(!queue.isEmpty()) {
            size = queue.size();

            while(0 < size--) {
                Ball current = queue.poll();

                rx = current.rx;
                ry = current.ry;
                bx = current.bx;
                by = current.by;

                for(int d = 0; d < 4; d++) {
                    Ball moved = move(d, rx, ry, bx, by);
                    if(moved == null || visited[moved.rx][moved.ry][moved.bx][moved.by]) continue;

                    visited[moved.rx][moved.ry][moved.bx][moved.by] = true;
                    queue.offer(moved);
                }
            }
            count++;
        }

        System.out.println(-1);
    }

    public static Ball move(int d, int rx, int ry, int bx, int by) {
        int x1, y1, x2, y2;

        boolean check = true;
        switch(d) {
            case 0:
                check = rx <= bx;
                break;
            case 1:
                check = by <= ry;
                break;
            case 2:
                check = bx <= rx;
                break;
            case 3:
                check = ry <= by;
                break;
        }

        if(check) {
            x1 = rx;
            y1 = ry;
            x2 = bx;
            y2 = by;
        } else {
            x1 = bx;
            y1 = by;
            x2 = rx;
            y2 = ry;
        }

        boolean flag1 = false;
        while(true) {
            x1 += dx[d];
            y1 += dy[d];

            if(board[x1][y1] == '#') {
                x1 -= dx[d];
                y1 -= dy[d];
                board[x1][y1] = 'G';
                break;
            }

            if(board[x1][y1] == 'O') {
                flag1 = true;
                break;
            }
        }

        boolean flag2 = false;
        while(true) {
            x2 += dx[d];
            y2 += dy[d];
            
            if(board[x2][y2] == '#' || board[x2][y2] == 'G') {
                x2 -= dx[d];
                y2 -= dy[d];
                break;
            }

            if(board[x2][y2] == 'O') {
                flag2 = true;
                break;
            }
        }

        if(!flag1) board[x1][y1] = '.';

        if(check) {
            if(flag2) return null;

            if(flag1) {
                System.out.println(count);
                System.exit(0);
            }

            return new Ball(x1, y1, x2, y2);
        } else {
            if (flag1) return null;

            if (flag2) {
                System.out.println(count);
                System.exit(0);
            }

            return new Ball(x2, y2, x1, y1);
        }
    }
}