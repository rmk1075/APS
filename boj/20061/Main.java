import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int point = 0;
    static int[][] Green, Blue;

    public static void main(String[] args) throws IOException {
        Green = new int[10][4];
        Blue = new int[10][4];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int t, x, y;
        StringTokenizer st;
        while (0 < N--) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            exe(0, Green, t, x, y, N + 1);
            exe(1, Blue, t, y, 3 - x, N + 1);
        }

        int sum = 0;
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (Green[i][j] != 0)
                    sum++;
                if (Blue[i][j] != 0)
                    sum++;
            }
        }
        System.out.println(point + "\n" + sum);
    }

    public static void exe(int b, int[][] map, int t, int x, int y, int idx) {
        List<int[]> block = getBlock(b, t, x, y);
        drop(map, block, idx);

        while (true) {
            int cnt = check(map);

            if (cnt == 0)
                break;

            point += cnt;
        }

        int check = 0;
        for (int i = 4; i < 6; i++) {
            if (map[i][0] != 0 || map[i][1] != 0 || map[i][2] != 0 || map[i][3] != 0)
                check++;
        }

        if (check == 0)
            return;

        for (int i = 0; i < check; i++) {
            map[9 - i][0] = map[9 - i][1] = map[9 - i][2] = map[9 - i][3] = 0;
        }

        for (int i = 9 - check; 3 < i; i--) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 0)
                    continue;

                map[i + check][j] = map[i][j];
                map[i][j] = 0;
            }
        }
    }

    public static List<int[]> getBlock(int type, int t, int x, int y) {
        List<int[]> block = new ArrayList<>();

        block.add(new int[] { x, y });
        switch (t) {
            case 2:
                block.add(type == 0 ? new int[] { x, y + 1 } : new int[] { x + 1, y });
                break;
            case 3:
                block.add(type == 0 ? new int[] { x + 1, y } : new int[] { x, y - 1 });
        }

        return block;
    }

    public static void drop(int[][] map, List<int[]> block, int idx) {
        boolean flag;
        int x, val = 1;
        while (true) {
            flag = false;
            for (int[] b : block) {
                x = b[0] + val;
                if (x == 10 || map[x][b[1]] != 0) {
                    flag = true;
                    break;
                }
            }

            if (flag)
                break;

            val++;
        }

        val--;
        for (int[] b : block) {
            map[b[0] + val][b[1]] = idx;
        }
    }

    public static int check(int[][] map) {
        int cnt = 0;
        for (int i = 6; i < 10; i++) {
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 0) {
                    flag = true;
                    break;
                }
            }

            if (flag)
                continue;

            for (int j = 0; j < 4; j++) {
                map[i][j] = 0;
            }

            for (int j = i; 3 < j; j--) {
                for (int k = 0; k < 4; k++) {
                    if (map[j][k] == 0)
                        continue;

                    map[j + 1][k] = map[j][k];
                    map[j][k] = 0;
                }
            }

            cnt++;
            i--;
        }

        return cnt;
    }
}