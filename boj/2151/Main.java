import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Location {
    int x, y, d;

    Location(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] visited;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        int[] src = new int[2];
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < N; j++) {
                if(map[i][j] == '#') {
                    src[0] = i;
                    src[1] = j;
                }
            }
        }

        
        visited = new int[4][N][N];
        for(int k = 0; k < 4; k++) {
            for(int i = 0; i < N; i++) {
                Arrays.fill(visited[k][i], Integer.MAX_VALUE);
            }
            visited[k][src[0]][src[1]] = 0;
        }

        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(src[0], src[1], 0));
        queue.add(new Location(src[0], src[1], 1));
        queue.add(new Location(src[0], src[1], 2));
        queue.add(new Location(src[0], src[1], 3));

        int minCount = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Location current = queue.poll();

            if(map[current.x][current.y] == '#' && (current.x != src[0] || current.y != src[1])) {
                minCount = Math.min(minCount, visited[current.d][current.x][current.y]);
                continue;
            }

            int x, y;
            if(map[current.x][current.y] == '!') {
                for(int d = 0; d < 4; d++) {
                    if(Math.abs(current.d - d) == 2 || current.d == d) continue;

                    x = current.x + dx[d];
                    y = current.y + dy[d];

                    if(x < 0 || y < 0 || x == N || y == N || map[x][y] == '*' || visited[d][x][y] <= visited[current.d][current.x][current.y] + 1) continue;

                    visited[d][x][y] = visited[current.d][current.x][current.y] + 1;
                    queue.add(new Location(x, y, d));
                }
            }

            x = current.x + dx[current.d];
            y = current.y + dy[current.d];

            if(x < 0 || y < 0 || x == N || y == N || map[x][y] == '*' || visited[current.d][x][y] <= visited[current.d][current.x][current.y]) continue;

            visited[current.d][x][y] = visited[current.d][current.x][current.y];
            queue.add(new Location(x, y, current.d));
        }

        System.out.println(minCount);
    }
}