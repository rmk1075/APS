import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Conditional {
    int x, y, d;
    List<int[]> area = new LinkedList<>();

    public Conditional(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int R, C, K, W, chocolate = 0;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[][] board, walls;
    static boolean[][] visited;
    static List<int[]> targets = new LinkedList<>();
    static List<Conditional> conds = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        walls = new int[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                int value = Integer.parseInt(st.nextToken());
                if(value == 0) continue;
                if(value == 5) targets.add(new int[]{i, j});
                else conds.add(new Conditional(i, j, value));
            }
        }
        
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        for(int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            if(t == 0) {
                walls[x][y] |= 1 << 4;
                walls[x - 1][y] |= 1 << 3;
            } else {
                walls[x][y] |= 1 << 2;
                walls[x][y + 1] |= 1 << 1;
            }
        }

        // save covered area for each conditional
        setConditionals();

        do {
            // 1. air blows
            blows();

            // 2. temp controlled
            control();

            // 3. temp decrease
            decrease();

            // 4. eat chocolate
            chocolate++;

            // 5. check targets
        } while(chocolate < 101 && !isOK());

        System.out.println(chocolate);
    }

    public static void setConditionals() {
        for(Conditional cond : conds) {
            int d = cond.d;
            int x = cond.x + dx[d];
            int y = cond.y + dy[d];
            cond.area.add(new int[]{x, y, 5});
            visited[x][y] = true;
            setConditional(cond, x, y, d, d < 3 ? 3 : 1, d < 3 ? 4 : 2, 4);
            for(int i = 0; i < R; i++) Arrays.fill(visited[i], false);
        }
    }

    public static void setConditional(Conditional cond, int x, int y, int d, int d1, int d2, int temp) {
        int nx, ny;

        // d
        nx = x + dx[d];
        ny = y + dy[d];
        if(isValid(nx, ny, d)) {
            cond.area.add(new int[]{nx, ny, temp});
            visited[nx][ny] = true;
            if(temp != 1) setConditional(cond, nx, ny, d, d1, d2, temp - 1);
        }

        // d1
        nx = x + dx[d1] + dx[d];
        ny = y + dy[d1] + dy[d];
        if(isValid(x + dx[d1], y + dy[d1], d1) && isValid(nx, ny, d)) {
            cond.area.add(new int[]{nx, ny, temp});
            visited[nx][ny] = true;
            if(temp != 1) setConditional(cond, nx, ny, d, d1, d2, temp - 1);
        }

        // d2
        nx = x + dx[d2] + dx[d];
        ny = y + dy[d2] + dy[d];
        if(isValid(x + dx[d2], y + dy[d2], d2) && isValid(nx, ny, d)) {
            cond.area.add(new int[]{nx, ny, temp});
            visited[nx][ny] = true;
            if(temp != 1) setConditional(cond, nx, ny, d, d1, d2, temp - 1);
        }
    }

    public static void blows() {
        for(Conditional cond : conds) {
            for(int[] a : cond.area) {
                board[a[0]][a[1]] += a[2];
            }
        }
    }

    public static boolean isValid(int x, int y, int d) {
        return -1 < x && -1 < y && x < R && y < C && ((walls[x][y] & (1 << d)) == 0) && !visited[x][y];
    }

    public static void control() {
        int[][] arr = new int[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(board[i][j] == 0) continue;
                for(int d = 1; d < 5; d++) {
                    int x = i + dx[d];
                    int y = j + dy[d];
                    if(!isValid(x, y, d) || board[i][j] <= board[x][y]) continue;
                    int temp = (int)Math.floor((board[i][j] - board[x][y]) / 4);
                    arr[i][j] -= temp;
                    arr[x][y] += temp;
                }
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) board[i][j] += arr[i][j];
        }
    }

    public static void decrease() {
        for(int i = 0; i < R; i++) {
            if(board[i][0] != 0) board[i][0]--;
            if(board[i][C - 1] != 0) board[i][C - 1]--;
        }

        for(int i = 1; i < C - 1; i++) {
            if(board[0][i] != 0) board[0][i]--;
            if(board[R - 1][i] != 0) board[R - 1][i]--;
        }
    }

    public static boolean isOK() {
        for(int[] target : targets) {
            if(board[target[0]][target[1]] < K) return false;
        }
        return true;
    }
}