import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M, maxVal;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] board, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        visited = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = 1;
                check1(i, j, 0, 0);
                visited[i][j] = 0;

                check2(i, j);
            }
        }

        System.out.println(maxVal);
    }

    public static void check1(int x, int y, int sum, int count) {

        if(count == 4) {
            maxVal = Math.max(maxVal, sum);
            return ;
        }

        for(int i = 0; i < 4; i++) {
            int x_ = x + dx[i];
            int y_ = y + dy[i];

            if(x_ < 0 || y_ < 0 || N <= x_ || M <= y_ || visited[x_][y_] == 1) continue;
            
            visited[x_][y_] = 1;
            check1(x_, y_, sum + board[x][y], count + 1);
            visited[x_][y_] = 0;
        }

    }

    public static void check2(int x, int y) {
        // '+'
        int sum = board[x][y];
        int side = 4;
        int minSide = Integer.MAX_VALUE;

        for(int i = 0; i < 4; i++) {
            int x_ = x + dx[i];
            int y_ = y + dy[i];

            if(side < 3) return ;

            if(x_ < 0 || y_ < 0 || N <= x_ ||M <= y_) {
                side--;
                continue;
            }

            minSide = Math.min(minSide, board[x_][y_]);
            sum += board[x_][y_];
        }

        if(side == 4) sum -= minSide;
        maxVal = Math.max(maxVal, sum);
    }
}