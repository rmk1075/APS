import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Location {
    int x, y;
    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

        // non RG
        int count = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) continue;

                Queue<Location> queue = new LinkedList<>();
                queue.offer(new Location(i, j));
                visited[i][j] = true;

                while(!queue.isEmpty()) {
                    Location current = queue.poll();

                    int x, y;
                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];

                        if(x < 0 || y < 0 || x == N || y == N || map[x][y] != map[current.x][current.y] || visited[x][y]) continue;

                        visited[x][y] = true;
                        queue.offer(new Location(x, y));
                    }
                }

                count++;
            }
        }
        System.out.print((count-1) + " ");

        // RG
        count = 1;
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) continue;

                Queue<Location> queue = new LinkedList<>();
                queue.offer(new Location(i, j));
                visited[i][j] = true;

                while(!queue.isEmpty()) {
                    Location current = queue.poll();

                    int x, y;
                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];

                        if(x < 0 || y < 0 || x == N || y == N || visited[x][y]) continue;
                        if((map[current.x][current.y] == 'B' && map[x][y] != 'B') || (map[current.x][current.y] != 'B' && map[x][y] == 'B')) continue;

                        visited[x][y] = true;
                        queue.offer(new Location(x, y));
                    }
                }

                count++;
            }
        }
        System.out.println((count-1));   
    }
}