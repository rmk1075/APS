import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, cnt = 0;

    Location(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] visited;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        visited = new int[N][M];
        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

            for(int j = 0; j < M; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(0, 0, 0));
        visited[0][0] = 0;

        int x, y;
        Location current;
        while(!queue.isEmpty()) {

            current = queue.poll();

            for(int d = 0; d < 4; d++) {
                x = current.x + dx[d];
                y = current.y + dy[d];

                if(x < 0 || y < 0 || N == x || M == y || visited[x][y] <= current.cnt) continue;

                if(x == N-1 && y == M-1) {
                    visited[x][y] = current.cnt;
                    continue;
                }

                visited[x][y] = current.cnt;
                if(map[x][y] == '0') {
                    queue.offer(new Location(x, y, current.cnt));
                } else {
                    queue.offer(new Location(x, y, current.cnt+1));
                }
            }
        }

        System.out.println(visited[N-1][M-1]);
    }
}