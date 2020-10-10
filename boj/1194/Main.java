import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, keys;
    Location(int x, int y, int keys) {
        this.x = x;
        this.y = y;
        this.keys = keys;
    }
}

public class Main {
    static int N, M, srcX = -1, srcY = -1;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static char[][] maze;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][];
        for(int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            if(srcX == -1) {
                for(int j = 0; j < M; j++) {
                    if(maze[i][j] == '0') {
                        srcX = i;
                        srcY = j;
                        break;
                    }
                }
            }
        }

        visited = new boolean[64][N][M];
        visited[0][srcX][srcY] = true;
        
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(srcX, srcY, 0));

        int size, time = 1;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();
                for(int d = 0; d < 4; d++) {
                    int x = current.x + dx[d], y = current.y + dy[d], keys = current.keys;
                    if(x < 0 || y < 0 || x == N || y == M || maze[x][y] == '#' || visited[keys][x][y]) continue;
                    if(maze[x][y] == '1') {
                        System.out.println(time);
                        return ;
                    }
    
                    if(maze[x][y] != '.' && maze[x][y] != '0') {
                        if(maze[x][y] < 'a') {                            
                            if((keys & (1 << (Character.toLowerCase(maze[x][y]) - 'a'))) == 0) continue;
                            visited[keys][x][y] = true;
                            queue.offer(new Location(x, y, keys));
                        } else {
                            int tempKeys = keys;
                            if((keys & (1 << (maze[x][y] - 'a'))) == 0) tempKeys = (keys | (1 << (maze[x][y] - 'a')));
                            visited[tempKeys][x][y] = true;
                            queue.offer(new Location(x, y, tempKeys));
                        }
                    } else {
                        visited[keys][x][y] = true;
                        queue.offer(new Location(x, y, keys));
                    }
                }
            }
            time++;
        }
        System.out.println(-1);
    }
}