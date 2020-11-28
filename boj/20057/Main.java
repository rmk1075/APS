import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 };
    static double[] tp = { 0.01, 0.01, 0.07, 0.07, 0.02, 0.02, 0.1, 0.1, 0.05 };
    static int[][] torx = { { -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 }, { -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 },
            { -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 }, { 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 } };
    static int[][] tory = { { 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 }, { -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 },
            { -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 }, { -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 } };
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 4][N + 4];

        StringTokenizer st;
        for (int i = 2; i < N + 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 2; j < N + 2; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        tornado();

        for (int i = 2; i < N + 2; i++) {
            for (int j = 2; j < N + 2; j++) {
                map[i][j] = 0;
            }
        }

        int answer = 0;
        for (int i = 0; i < N + 4; i++) {
            for (int j = 0; j < N + 4; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    public static void tornado() {
        int x = N / 2 + 2, y = N / 2 + 2, d = 0, len = 1, tempLen = 0, cnt = 0;

        while (x != 2 || y != 2) {
            x += dx[d];
            y += dy[d];

            push(x, y, d);

            if (len == ++tempLen) {
                if (++cnt % 2 == 0) {
                    len++;
                }

                tempLen = 0;
                d = (d + 1) % 4;
            }
        }

    }

    public static void push(int x, int y, int d) {
        int temp, sum = 0, val = map[x][y];
        map[x][y] = 0;
        for (int i = 0; i < 9; i++) {
            temp = (int) (val * tp[i]);
            sum += temp;
            map[x + torx[d][i]][y + tory[d][i]] += temp;
        }

        map[x + torx[d][9]][y + tory[d][9]] += val - sum;
    }
}
