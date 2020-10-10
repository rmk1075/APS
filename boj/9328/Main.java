import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class SK {
    int x, y;

    SK(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int T, H, W, keyCnt = 0;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int[] keys = new int[26];
    static char[][] map;
    static int[][] update;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while (0 < T--) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H + 2][W + 2];
            update = new int[H + 2][W + 2];

            for (int i = 0; i < H + 2; i++) {
                Arrays.fill(map[i], '.');
                Arrays.fill(update[i], -1);
            }

            for (int i = 1; i < H + 1; i++) {
                int j = 1;
                for (char ch : br.readLine().toCharArray()) {
                    map[i][j] = ch;
                    j++;
                }
            }

            keyCnt = 0;
            keys = new int[26];
            for (char ch : br.readLine().toCharArray()) {
                if (ch == '0')
                    break;
                keys[ch - 'a'] = 1;
                keyCnt++;
            }

            Queue<SK> queue = new LinkedList<>();
            queue.add(new SK(0, 0));
            update[0][0] = keyCnt;

            int count = 0;
            while (!queue.isEmpty()) {
                SK sk = queue.poll();

                int x, y;
                for (int d = 0; d < 4; d++) {
                    x = sk.x + dx[d];
                    y = sk.y + dy[d];

                    if (x < 0 || y < 0 || x == H + 2 || y == W + 2 || map[x][y] == '*' || update[sk.x][sk.y] <= update[x][y]) continue;

                    // visite more than once???
                    if (map[x][y] == '$') {
                        if (update[x][y] == -1) count++;
                        update[x][y] = keyCnt;
                        queue.add(new SK(x, y));
                        continue;
                    }

                    if (map[x][y] != '.') {
                        if (Character.isUpperCase(map[x][y])) {
                            if (keys[Character.toLowerCase(map[x][y]) - 'a'] == 0) continue;
                        } else if (keys[map[x][y] - 'a'] == 0) {
                            keys[map[x][y] - 'a'] = 1;
                            keyCnt++;

                            update[x][y] = keyCnt;
                            queue.add(new SK(x, y));
                            continue;
                        }
                    }

                    update[x][y] = keyCnt;
                    queue.add(new SK(x, y));
                }
            }

            System.out.println(count);
        }
    }
}