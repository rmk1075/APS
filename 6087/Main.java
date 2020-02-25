import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, d;
    
    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Location(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int W, H;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] visited;
    static Location[] C = new Location[2];
    static char[][] map;
    
    // cnt + 1 when the direction changed
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];

        int idx = 0;
        for(int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < W; j++) {
                if(map[i][j] == 'C') {
                    C[idx++] = new Location(i, j);
                }
            }
        }

        Queue<Location> queue = new LinkedList<>();
        visited = new int[4][H][W];
        for(int k = 0; k < 4; k++) {
            for(int i = 0; i < H; i++) {
                Arrays.fill(visited[k][i], Integer.MAX_VALUE);
            }
        }
        
        queue.offer(new Location(C[0].x, C[0].y, 0));
        queue.offer(new Location(C[0].x, C[0].y, 1));
        queue.offer(new Location(C[0].x, C[0].y, 2));
        queue.offer(new Location(C[0].x, C[0].y, 3));
        visited[0][C[0].x][C[0].y] = 0;
        visited[1][C[0].x][C[0].y] = 0;
        visited[2][C[0].x][C[0].y] = 0;
        visited[3][C[0].x][C[0].y] = 0;
        while(!queue.isEmpty()) {
            Location current = queue.poll();

            int x, y;
            for(int d = 0; d < 4; d++) {
                x = current.x + dx[d];
                y = current.y + dy[d];

                if(x < 0 || y < 0 || x == H || y == W || map[x][y] == '*' || visited[d][x][y] <= visited[current.d][current.x][current.y]) continue;

                if(d != current.d) visited[d][x][y] = visited[current.d][current.x][current.y] + 1;
                else visited[d][x][y] = visited[current.d][current.x][current.y];

                if(map[x][y] == 'C') continue;

                queue.offer(new Location(x, y, d));
            }
        }

        int minCount = Integer.MAX_VALUE;
        for(int k = 0; k < 4; k++) {
            minCount = Math.min(minCount, visited[k][C[1].x][C[1].y]);
        }

        System.out.println(minCount);
    }
}