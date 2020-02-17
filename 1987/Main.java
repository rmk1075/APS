import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, ans = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] alphabet;
    static int[][] visited;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new int[R][C];
        alphabet = new int['Z'-'A'+1];

        for(int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visited[0][0] = 1;
        alphabet[board[0][0] - 'A'] = 1;
        find(0, 0, 1);
        System.out.println(ans);
    }

    public static void find(int x, int y, int count) {

        ans = Math.max(ans, count);

        int x_, y_;
        for(int d = 0; d < 4; d++) {
            x_ = x + dx[d];
            y_ = y + dy[d];

            if(x_ < 0 || y_ < 0 || R == x_ || C == y_ || visited[x_][y_] == 1 || alphabet[board[x_][y_] - 'A'] == 1) continue;

            visited[x_][y_] = 1;
            alphabet[board[x_][y_] - 'A'] = 1;
            find(x_, y_, count+1);
            visited[x_][y_] = 0;
            alphabet[board[x_][y_] - 'A'] = 0;
        }

    }
}