import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, point = 0;
    static int[][] map;
    static Map<Integer, List<int[]>> Green = new HashMap<>(), Blue = new HashMap<>();

    // TODO:
    public static void printMap() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("==================");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[10][10];

        StringTokenizer st;
        int t, x, y;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            List<int[]> blockList = createBlockList(t, x, y);

            blue(i + 1, t, blockList);
            green(i + 1, t, blockList);

            // TODO:
            printMap();
        }

        int answer = 0;
        for (int[] row : map) {
            for (int i = 0; i < 10; i++) {
                if (row[i] != 0)
                    answer++;
            }
        }

        System.out.println(point + "\n" + answer);
    }

    public static List<int[]> createBlockList(int t, int x, int y) {
        List<int[]> blockList = new ArrayList<>();

        switch (t) {
            case 1:
                blockList.add(new int[] { x, y });
                break;

            case 2:
                blockList.add(new int[] { x, y });
                blockList.add(new int[] { x, y + 1 });
                break;

            case 3:
                blockList.add(new int[] { x, y });
                blockList.add(new int[] { x + 1, y });
                break;
            default:
                break;
        }

        return blockList;
    }

    public static void green(int idx, int t, List<int[]> blockList) {
        move(0, idx, blockList, 1, 0);

        int check = 0;
        while (true) {
            check = checkGreen();

            if (check == 0)
                break;

            point += check;
            downGreen();
        }

        List<int[]> temp = new ArrayList<>();
        while (map[5][0] != 0 || map[5][1] != 0 || map[5][2] != 0 || map[5][3] != 0) {
            for (int i = 0; i < 4; i++) {
                if (map[9][i] == 0)
                    continue;

                int key = map[9][i];
                temp.clear();
                for (int[] g : Green.get(key)) {
                    if (g[0] != i || g[1] != 9)
                        temp.add(g);
                }

                if (temp.isEmpty())
                    Green.remove(key);
                else
                    Green.put(key, temp);

                map[9][i] = 0;
            }
            downGreen();
        }
    }

    public static void blue(int idx, int t, List<int[]> blockList) {
        move(1, idx, blockList, 0, 1);

        int check = 0;
        while (true) {
            check = checkBlue();

            if (check == 0)
                break;

            point += check;
            downBlue();
        }

        List<int[]> temp = new ArrayList<>();
        while (map[0][5] != 0 || map[1][5] != 0 || map[2][5] != 0 || map[3][5] != 0) {
            for (int i = 0; i < 4; i++) {
                if (map[i][9] == 0)
                    continue;

                int key = map[i][9];
                temp.clear();
                for (int[] b : Blue.get(key)) {
                    if (b[0] != i || b[1] != 9)
                        temp.add(b);
                }

                if (temp.isEmpty())
                    Blue.remove(key);
                else
                    Blue.put(key, temp);

                map[i][9] = 0;
            }
            downBlue();
        }

    }

    public static void move(int type, int idx, List<int[]> blocks, int dx, int dy) {
        if (blocks.isEmpty())
            return;

        List<int[]> blockList = new ArrayList<>();
        blockList.addAll(blocks);

        List<int[]> tempList = new ArrayList<>();
        while (true) {
            boolean flag = false;
            for (int[] block : blockList) {
                int x = block[0] + dx, y = block[1] + dy;
                if (x == 10 || y == 10 || map[x][y] != 0) {
                    flag = true;
                    break;
                }

                tempList.add(new int[] { x, y });
            }

            if (flag)
                break;

            blockList.clear();
            blockList.addAll(tempList);
            tempList.clear();
        }

        if (type == 0)
            Green.put(idx, blockList);
        else
            Blue.put(idx, blockList);

        for (int[] block : blockList) {
            map[block[0]][block[1]] = idx;
        }
    }

    public static int checkGreen() {
        int result = 0;
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
                int idx = map[i][j];
                map[i][j] = 0;

                List<int[]> temp = Green.get(idx);
                temp.remove(new int[] { i, j });
                if (temp.isEmpty())
                    Green.remove(idx);
                else
                    Green.put(idx, temp);
            }
            result++;
        }

        return result;
    }

    public static int checkBlue() {
        int result = 0;
        for (int i = 6; i < 10; i++) {
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                if (map[j][i] == 0) {
                    flag = true;
                    break;
                }
            }

            if (flag)
                continue;

            for (int j = 0; j < 4; j++) {
                int idx = map[j][i];
                map[i][j] = 0;

                List<int[]> temp = Blue.get(idx);
                temp.remove(new int[] { j, i });
                if (temp.isEmpty())
                    Blue.remove(idx);
                else
                    Blue.put(idx, temp);
            }
            result++;
        }

        return result;
    }

    public static void downGreen() {
        for (int i = 8; 3 < i; i--) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 0)
                    continue;

                int idx = map[i][j];
                List<int[]> blockList = Green.get(idx);
                for (int[] block : blockList) {
                    map[block[0]][block[1]] = 0;
                }

                move(0, idx, blockList, 1, 0);
            }
        }
    }

    public static void downBlue() {
        for (int i = 8; 3 < i; i--) {
            for (int j = 0; j < 4; j++) {
                if (map[j][i] == 0)
                    continue;

                int idx = map[j][i];
                List<int[]> blockList = Blue.get(idx);
                for (int[] block : blockList) {
                    map[block[0]][block[1]] = 0;
                }

                move(1, idx, blockList, 0, 1);
            }
        }
    }
}
