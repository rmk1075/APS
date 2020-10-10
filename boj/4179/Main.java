import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int R, C, dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
    static char[][] maze;
    static Queue<Location> jh = new LinkedList<>();
    static Queue<Location> fire = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R][C];
        for(int i = 0; i < R; i++) {
            maze[i] = br.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                switch(maze[i][j]) {
                    case 'J':
                        jh.offer(new Location(i, j));
                        break;
                    case 'F':
                        fire.offer(new Location(i, j));
                        break;
                }
            }
        }

        Location current;
        int x, y, time = 1;
        while(!jh.isEmpty()) {
            int size = jh.size();
            while(0 < size--) {
                current = jh.poll();
                if(maze[current.x][current.y] == 'F') continue;

                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];
                    if(x < 0 || y < 0 || x == R || y == C) {
                        System.out.println(time);
                        return ;
                    }

                    if(maze[x][y] != '.') continue;
                    maze[x][y] = 'J';
                    jh.offer(new Location(x, y));
                }
            }

            size = fire.size();
            while(0 < size--) {
                current = fire.poll();
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];
                    if(x < 0 || y < 0 || x == R || y == C || maze[x][y] == '#' || maze[x][y] == 'F') continue;
                    maze[x][y] = 'F';
                    fire.offer(new Location(x, y));
                }
            }
            time++;
        }
        System.out.println("IMPOSSIBLE");
    }
}