import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M, x, y, K;
    static int[] dice = {0, 0, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken());

            int x_ = x + dx[direction-1];
            int y_ = y + dy[direction-1];

            if(x_ < 0 || N <= x_ || y_ < 0 || M <= y_) continue;

            x = x_;
            y = y_;

            // 1: east, 2: west, 3: north, 4: south
            int temp = -1;
            if(direction == 1) {
                temp = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = temp;
            } else if(direction == 2) {
                temp = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = temp;
            } else if(direction == 3) {
                temp = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
            } else {
                temp = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = temp;
            }

            if(map[x][y] == 0) {
                map[x][y] = dice[5];
            } else {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }

            System.out.println(dice[0]);
        }
    }
}