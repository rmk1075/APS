import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, srcX, srcY;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                srcX = i;
                srcY = j;
                visited = new boolean[N][M];
                visited[i][j] = true;
                find(i, j, map[i][j], 1);
                visited[i][j] = false;
            }
        }
        System.out.println("No");
    }

    public static void find(int curX, int curY, char color, int count) {
        for (int d = 0; d < 4; d++) {
            int x = curX + dx[d], y = curY + dy[d];

            if (4 <= count && x == srcX && y == srcY) {
                System.out.println("Yes");
                System.exit(0);
            }

            if (x < 0 || y < 0 || x == N || y == M || map[x][y] != color || visited[x][y]) continue;
            visited[x][y] = true;
            find(x, y, color, count + 1);
            visited[x][y] = false;
        }
    }
}