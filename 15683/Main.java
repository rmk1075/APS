import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M, minVal = Integer.MAX_VALUE, numOfCCTV = 0, numOfEmpty = 0;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[][] cctvs = new int[8][4];
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs[numOfCCTV][0] = i;
                    cctvs[numOfCCTV][1] = j;
                    cctvs[numOfCCTV++][2] = map[i][j];
                } else if (map[i][j] == 0)
                    numOfEmpty++;
            }
        }

        directions(0);

        System.out.println(minVal);
    }

    public static void directions(int count) {

        if (count == numOfCCTV) {
            cal();
            return;
        }

        if (cctvs[count][2] != 5) {
            for (int i = 0; i < 4; i++) {
                cctvs[count][3] = i;
                directions(count + 1);
            }
        } else {
            directions(count + 1);
        }
    }

    public static void cal() {
        int[][] tempMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        int checked = 0;
        for (int i = 0; i < numOfCCTV; i++) {
            int d = cctvs[i][3];
            int d_ = 3 < d + 1 ? 0 : d + 1;
            int x, y;
            switch (cctvs[i][2]) {
            case 5:
                x = cctvs[i][0];
                y = cctvs[i][1];

                while (true) {
                    x += dx[d_];
                    y += dy[d_];

                    if (x < 0 || y < 0 || N <= x || M <= y || tempMap[x][y] == 6)
                        break;
                    if (tempMap[x][y] != 0)
                        continue;
                    tempMap[x][y] = -1;
                    checked++;
                }

            case 4:
                x = cctvs[i][0];
                y = cctvs[i][1];

                while (true) {
                    x -= dx[d_];
                    y -= dy[d_];

                    if (x < 0 || y < 0 || N <= x || M <= y || tempMap[x][y] == 6)
                        break;
                    if (tempMap[x][y] != 0)
                        continue;
                    tempMap[x][y] = -1;
                    checked++;
                }

            case 2:
                x = cctvs[i][0];
                y = cctvs[i][1];

                while (true) {
                    x -= dx[d];
                    y -= dy[d];

                    if (x < 0 || y < 0 || N <= x || M <= y || tempMap[x][y] == 6)
                        break;
                    if (tempMap[x][y] != 0)
                        continue;
                    tempMap[x][y] = -1;
                    checked++;
                }

            case 1:
                x = cctvs[i][0];
                y = cctvs[i][1];

                while (true) {
                    x += dx[d];
                    y += dy[d];

                    if (x < 0 || y < 0 || N <= x || M <= y || tempMap[x][y] == 6)
                        break;
                    if (tempMap[x][y] != 0)
                        continue;
                    tempMap[x][y] = -1;
                    checked++;
                }

                break;
            case 3:
                x = cctvs[i][0];
                y = cctvs[i][1];

                while (true) {
                    x += dx[d];
                    y += dy[d];

                    if (x < 0 || y < 0 || N <= x || M <= y || tempMap[x][y] == 6)
                        break;
                    if (tempMap[x][y] != 0)
                        continue;
                    tempMap[x][y] = -1;
                    checked++;
                }

                x = cctvs[i][0];
                y = cctvs[i][1];
                d_ = d - 1 < 0 ? 3 : d - 1;

                while (true) {
                    x += dx[d_];
                    y += dy[d_];

                    if (x < 0 || y < 0 || N <= x || M <= y || tempMap[x][y] == 6)
                        break;
                    if (tempMap[x][y] != 0)
                        continue;
                    tempMap[x][y] = -1;
                    checked++;
                }
            }
        }

        minVal = Math.min(minVal, numOfEmpty - checked);
    }
}