import java.util.Scanner;

public class Main {

    static int N = 19;
    static int[] dx = { 0, 1, 1, -1 };
    static int[] dy = { 1, 1, 0, 1 };
    static int[] dx_ = { 0, -1, -1, 1 };
    static int[] dy_ = { -1, -1, 0, -1 };
    static int[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        sc.close();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[j][i] == 1 || board[j][i] == 2) {

                    if (check(j, i, board[j][i])) {
                        System.out.println(board[j][i]);
                        System.out.println((j + 1) + " " + (i + 1));
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println(0);
    }

    public static boolean check(int x, int y, int color) {

        for (int d = 0; d < 4; d++) {
            int count = 0;
            int x_ = x;
            int y_ = y;

            while (0 <= x_ && 0 <= y_ && x_ < N && y_ < N && board[x_][y_] == color) {

                x_ += dx[d];
                y_ += dy[d];
                count++;
            }

            x_ = x;
            y_ = y;
            while (0 <= x_ && 0 <= y_ && x_ < N && y_ < N && board[x_][y_] == color) {

                x_ += dx_[d];
                y_ += dy_[d];
                count++;
            }

            count--;

            if (count == 5) {
                return true;
            }
        }

        return false;
    }
}