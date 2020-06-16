import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int sum, fishes[][] = new int[17][2], map[][][] = new int[4][4][2]; // 0: index, 1: direction
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        doInit();
        dfs(sum, fishes, map);
        System.out.println(sum);
    }

    public static void dfs(int sum_, int[][] fishes_, int[][][] map_) {
        int x, y, d;
        int[][] fishes__ = new int[17][2];
        for(int i = 0; i < 17; i++) {
            fishes__[i][0] = fishes_[i][0];
            fishes__[i][1] = fishes_[i][1];
        }
        
        int[][][] map__ = new int[4][4][2];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                map__[i][j][0] = map_[i][j][0];
                map__[i][j][1] = map_[i][j][1];
            }
        }
        
        // fishes
        for(int i = 1; i < 17; i++) {
            int fx = fishes__[i][0], fy = fishes__[i][1];
            if(map__[fx][fy][0] != i) continue;

            d = map__[fx][fy][1];
            do {
                x = fx + dx[d];
                y = fy + dy[d];
                if(x < 0 || y < 0 || x == 4 || y == 4 || map__[x][y][0] == 0) {
                    d--;
                    if(d < 1) d = 8;
                    continue;
                }

                if(map__[x][y][0] == -1) {
                    map__[fx][fy][0] = -1;
                    map__[x][y][0] = i;
                    map__[x][y][1] = d;
                    fishes__[i][0] = x;
                    fishes__[i][1] = y;
                    break;
                }
                
                // change
                int tempIdx = map__[x][y][0], tempDir = map__[x][y][1], tempX = x, tempY = y;

                fishes__[tempIdx][0] = fx;
                fishes__[tempIdx][1] = fy;
                
                fishes__[i][0] = tempX;
                fishes__[i][1] = tempY;

                map__[x][y][0] = i;
                map__[x][y][1] = d;

                map__[fx][fy][0] = tempIdx;
                map__[fx][fy][1] = tempDir;
                
                break;
            } while(d != map__[fx][fy][1]);
        }

        // shark
        int sharkX = fishes__[0][0], sharkY = fishes__[0][1];
        map__[sharkX][sharkY][0] = -1;
        d = map__[sharkX][sharkY][1];
        x = sharkX;
        y = sharkY;
        while(true) {
            x += dx[d];
            y += dy[d];
            if(x < 0 || y < 0 || x == 4 || y == 4) break;
            if(map__[x][y][0] == -1) continue;

            // eat
            int fish = map__[x][y][0];
            map__[x][y][0] = 0;
            fishes__[0][0] = x;
            fishes__[0][1] = y;

            dfs(sum_ + fish, fishes__, map__);
            
            map__[x][y][0] = fish;
        }

        sum = Math.max(sum, sum_);
    }

    public static void doInit() throws IOException {
        input();

        sum = map[0][0][0];
        map[0][0][0] = 0;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

                map[i][j][0] = a; // fish no
                map[i][j][1] = b; // fish dir

                fishes[a][0] = i; // a-th fish loc i
                fishes[a][1] = j; // a-th fish loc j
            }
        }
    }
}