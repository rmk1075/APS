import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, Q, L, sum = 0;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
    static int[][] map;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];
        for (int i = 0; i < (int) Math.pow(2, N); i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < (int) Math.pow(2, N); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L = Integer.parseInt(st.nextToken());
            divide();
            melting();
        }

        System.out.println(sum + "\n" + ice());
    }

    public static int ice() {
        Queue<int[]> queue = new LinkedList<>();
        int x, y, maxCnt = 0, cnt = 0, len = (int) Math.pow(2, N);
        int[][] temp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map[i][j] == 0 || temp[i][j] == 1)
                    continue;

                queue.offer(new int[] { i, j });
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    for (int d = 0; d < 4; d++) {
                        x = current[0] + dx[d];
                        y = current[1] + dy[d];
                        if (x < 0 || y < 0 || x == len || y == len || temp[x][y] == 1 || map[x][y] == 0)
                            continue;

                        cnt++;
                        temp[x][y] = 1;
                        queue.offer(new int[] { x, y });
                    }
                }

                maxCnt = Math.max(maxCnt, cnt);
                cnt = 0;
            }
        }

        return maxCnt;
    }

    public static void melting() {
        list.clear();
        int len = (int) Math.pow(2, N);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map[i][j] == 0 || check(i, j, len))
                    continue;

                list.add(new int[] { i, j });
            }
        }

        for (int[] l : list) {
            map[l[0]][l[1]]--;
            sum--;
        }
    }

    public static boolean check(int x, int y, int len) {
        int tx, ty, cnt = 0;
        for (int d = 0; d < 4; d++) {
            tx = x + dx[d];
            ty = y + dy[d];
            if (tx < 0 || ty < 0 || tx == len || ty == len || map[tx][ty] == 0)
                continue;
            cnt++;
        }

        if (cnt < 3)
            return false;

        return true;
    }

    public static void divide() {
        int len = (int) Math.pow(2, L);
        for (int i = 0; i < Math.pow(2, N); i += len) {
            for (int j = 0; j < Math.pow(2, N); j += len) {
                circle(i, j, len);
            }
        }
    }

    public static void circle(int r, int c, int len) {
        while (1 < len) {
            change(r++, c++, len);
            len -= 2;
        }
    }

    public static void change(int r, int c, int len) {
        int[] temp = new int[len - 1];
        for (int i = 0; i < len - 1; i++) {
            temp[i] = map[r][c + i];
        }

        int r1, r2, c1, c2;
        r1 = r + len - 1;
        c1 = c;
        r2 = r;
        c2 = c;
        for (int i = 0; i < len - 1; i++) {
            map[r2][c2 + i] = map[r1 - i][c1];
        }

        r1 = r + len - 1;
        c1 = c + len - 1;
        r2 = r + len - 1;
        c2 = c;
        for (int i = 0; i < len - 1; i++) {
            map[r2 - i][c2] = map[r1][c1 - i];
        }

        r1 = r;
        c1 = c + len - 1;
        r2 = r + len - 1;
        c2 = c + len - 1;
        for (int i = 0; i < len - 1; i++) {
            map[r2][c2 - i] = map[r1 + i][c1];
        }

        for (int i = 0; i < len - 1; i++) {
            map[r + i][c + len - 1] = temp[i];
        }
    }
}