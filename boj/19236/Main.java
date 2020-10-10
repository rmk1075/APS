import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int sum;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        int fishes[][] = new int[17][3], map[][] = new int[4][4]; // fishes: 0 - x, 1 - y, 2 - dir | map: idx
        doInit(fishes, map);
        dfs(fishes, map, sum);
        System.out.println(sum);
    }

    public static void dfs(int[][] fishes_, int[][] map_, int val) {
        int fishes[][] = new int[17][3], map[][] = new int[4][4];
        
        // copy arrays
        copy(fishes_, map_, fishes, map);

        // fish move
        fish(fishes, map);
        
        // shark move
        shark(fishes, map, val);

        sum = Math.max(sum, val);
    }

    public static void shark(int[][] fishes, int[][] map, int val) {
        int x = fishes[0][0], y = fishes[0][1], d = fishes[0][2];
        map[x][y] = -1;
        while(true) {
            x += dx[d];
            y += dy[d];

            if(x < 0 || y < 0 || 3 < x || 3 < y) break;
            if(map[x][y] == -1) continue;

            int feed = map[x][y];
            map[x][y] = 0;
            fishes[0][0] = x;
            fishes[0][1] = y;
            fishes[0][2] = fishes[feed][2];

            dfs(fishes, map, val + feed);

            map[x][y] = feed;
        }
    }

    public static void fish(int[][] fishes, int[][] map) {
        int x, y, d;
        for(int i = 1; i < 17; i++) {
            x = fishes[i][0];
            y = fishes[i][1];
            if(map[x][y] != i) continue;

            while(true) {
                int x_ = x + dx[fishes[i][2]], y_ = y + dy[fishes[i][2]];
                if(x_ < 0 || y_ < 0 || x_ == 4 || y_ == 4 || map[x_][y_] == 0) {
                    fishes[i][2]++;
                    if(8 < fishes[i][2]) fishes[i][2] = 1;
                } else {
                    // change
                    if(map[x_][y_] == -1) {
                        // empty
                        map[x][y] = -1;
                        map[x_][y_] = i;
    
                        fishes[i][0] = x_;
                        fishes[i][1] = y_;
                    } else {
                        // non-empty
                        int target = map[x_][y_], a = fishes[i][0], b = fishes[i][1], c = fishes[i][2];
                        fishes[i][0] = x_;
                        fishes[i][1] = y_;
                        // fishes[i][2] = fishes[target][2];
                        map[x_][y_] = i;

                        fishes[target][0] = a;
                        fishes[target][1] = b;
                        // fishes[target][2] = c;
                        map[a][b] = target;
                    }
                    break;
                }
            }
        }
    }

    public static void copy(int[][] A_, int[][] B_, int [][] A, int[][] B) {
        for(int i = 0; i < 17; i++) {
            A[i][0] = A_[i][0];
            A[i][1] = A_[i][1];
            A[i][2] = A_[i][2];
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) B[i][j] = B_[i][j];
        }
    }

    public static void doInit(int[][] fishes, int[][] map) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // read input
        for(int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                int idx = Integer.parseInt(st.nextToken()), dir = Integer.parseInt(st.nextToken());

                fishes[idx][0] = i;
                fishes[idx][1] = j;
                fishes[idx][2] = dir;

                map[i][j] = idx;
            }
        }

        // shark init
        sum = map[0][0];
        fishes[0][2] = fishes[sum][2];
        map[0][0] = 0;
    }
}