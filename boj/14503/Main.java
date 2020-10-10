import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M, count, zeros;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        zeros = 0;
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) zeros++;
            }
        }
        
        count = 0;
        find(r, c, d);
    }

    public static boolean check(int x, int y, int d, int index) {
        int d_ = d - index < 0 ? d - index + 4 : d - index;
        int nx = x + dx[d_];
        int ny = y + dy[d_];

        if(nx < 0 || ny < 0 || N <= nx || M <= ny || map[nx][ny] != 0) return false;

        return true;
    }

    public static void find(int x, int y, int d) {

        while(true) {
            // clean current square
            if(map[x][y] == 0) {
                map[x][y] = 2;
                count++;
            }

            if(count == zeros) {
                System.out.println(count);
                System.exit(0);
            }

            // check next square
            boolean isEmpty = false;
            for(int i = 1; i < 5; i++) {
                if(isEmpty = check(x, y, d, i)) {
                    d = d - i < 0 ? d - i + 4 : d - i;
                    break;
                }
            }

            if(isEmpty) {

                x += dx[d];
                y += dy[d];

            } else {

                // check back side
                int d_ = d - 2 < 0 ? d - 2 + 4 : d - 2;
                if(x + dx[d_] < 0 || y + dy[d_] < 0 || N <= x + dx[d_] || M <= y + dx[d_] || map[x + dx[d_]][y + dy[d_]] == 1) {
                    System.out.println(count);
                    System.exit(0);
                }

                x += dx[d_];
                y += dy[d_];
            }
        }
    }
}