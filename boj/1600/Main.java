import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, cnt;

    Location(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {
    static int K, W, H;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] jx = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] jy = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[K+1][H][W];
        
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(H == 1 && W == 1) {
            System.out.println(0);
            System.exit(0);
        }

        visited[0][0][0] = true;
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(0, 0, 0));

        int size, count = 1;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();

                int x, y;
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];

                    if(x == H-1 && y == W-1) {
                        System.out.println(count);
                        System.exit(0);
                    }

                    if(x < 0 || y < 0 || H == x || W == y || map[x][y] == 1 || visited[current.cnt][x][y]) continue;

                    visited[current.cnt][x][y] = true;
                    queue.add(new Location(x, y, current.cnt));
                }

                if(current.cnt == K) continue;

                for(int d = 0; d < 8; d++) {
                    x = current.x + jx[d];
                    y = current.y + jy[d];

                    if(x == H-1 && y == W-1) {
                        System.out.println(count);
                        System.exit(0);
                    }

                    if(x < 0 || y < 0 || H <= x || W <= y || map[x][y] == 1 || visited[current.cnt+1][x][y]) continue;

                    visited[current.cnt+1][x][y] = true;
                    queue.add(new Location(x, y, current.cnt+1));
                }
            }

            count++;
        }

        System.out.println(-1);
    }
}