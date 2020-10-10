import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int R, C, N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] cluster;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for(int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int height;
        for(int i = 0; i < N; i++) {
            height = R - Integer.parseInt(st.nextToken());

            // throw a spear
            if(i % 2 == 0) shoot(0, height); // l to r
            else shoot(1, height); // r to l

            // clustering
            cluster = new int[R][C];
            clustering();
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) System.out.print(map[i][j]);
            System.out.println();
        }
    }

    public static int bfs(int x, int y, int index) {
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(x, y));
        cluster[x][y] = index;

        int bottom = 0;
        while(!queue.isEmpty()) {
            Location current = queue.poll();

            int x_, y_;
            for(int d = 0; d < 4; d++) {
                x_ = current.x + dx[d];
                y_ = current.y + dy[d];

                if(x_ < 0 || y_ < 0 || x_ == R || y_ == C || map[x_][y_] == '.' || cluster[x_][y_] != 0) continue;

                bottom = Math.max(bottom, x_);

                cluster[x_][y_] = index;
                queue.offer(new Location(x_, y_));
            }
        }

        return bottom;
    }

    public static void clustering() {
        int idx = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'x' && cluster[i][j] == 0) {
                    if(bfs(i, j, idx) != R-1) list.add(idx);
                    idx++;
                }
            }
        }

        // drop
        boolean isChanged;
        int[][] tempCluster = new int[R][C];
        char[][] tempMap = new char[R][C];
        while(!list.isEmpty()) {
            isChanged = false;
            for(int i = 0; i < R; i++) {
                tempCluster[i] = Arrays.copyOf(cluster[i], cluster[i].length);
                tempMap[i] = Arrays.copyOf(map[i], map[i].length);
            }

            loop1:
            for(int i = R-1; -1 < i; i--) {
                for(int j = 0; j < C; j++) {
                    if(list.contains(tempCluster[i][j])) {
                        if(i+1 == R || (tempMap[i+1][j] == 'x' && !list.contains(tempCluster[i+1][j]))) {
                            list.remove((Integer)cluster[i][j]);
                            isChanged = true;
                            break loop1;
                        }

                        tempMap[i+1][j] = tempMap[i][j];
                        tempMap[i][j] = '.';
                        tempCluster[i+1][j] = tempCluster[i][j];
                        tempCluster[i][j] = 0;

                    }
                }
            }

            if(isChanged) continue;

            for(int i = 0; i < R; i++) {
                map[i] = Arrays.copyOf(tempMap[i], tempMap[i].length);
                cluster[i] = Arrays.copyOf(tempCluster[i], tempCluster[i].length);
            }
        }
    }

    public static void shoot(int direction, int height) {
        if(direction == 0) {
            for(int i = 0; i < C; i++) {
                if(map[height][i] == 'x') {
                    map[height][i] = '.';
                    break;
                }
            }
        } else {
            for(int i = C-1; -1 < i; i--) {
                if(map[height][i] == 'x') {
                    map[height][i] = '.';
                    break;
                }
            }
        }
    }
}