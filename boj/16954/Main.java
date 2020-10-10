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
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1}, dy = {0, 1, 0, -1, -1, 1, -1, 1};
    static char[][] map = new char[8][8];
    static Queue<Location> walls = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 8; i++) map[i] = br.readLine().toCharArray();
        for(int i = 7; -1 < i; i--) {
            for(int j = 0; j < 8; j++) {
                if(map[i][j] == '#')walls.offer(new Location(i, j));
            }
        }
        
        int[] visited = new int[8];
        visited[7] |= (1 << 0);

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(7, 0));
        
        boolean isArrived = false;
        loop: while(!queue.isEmpty()) {
            // wookjae
            int size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();
                if(map[current.x][current.y] == '#') continue;

                for(int d = 0; d < 8; d++) {
                    int x = current.x + dx[d], y = current.y + dy[d];
                    if(x < 0 || y < 0 || x == 8 || y == 8 || map[x][y] == '#' || ((visited[x] & (1 << y)) != 0)) continue;
                    if(x == 0 && y == 7) {
                        isArrived = true;
                        break loop;
                    }

                    visited[x] |= (1 << y);
                    queue.offer(new Location(x, y));
                }

                queue.offer(current);
            }

            // wall
            size = walls.size();
            while(0 < size--) {
                Location current = walls.poll();

                map[current.x][current.y] = '.';
                if(current.x == 7) continue;
                
                current.x++;
                map[current.x][current.y] = '#';
                visited[current.x] &= ~(1 << current.y);
                walls.offer(current);
            }
        }

        if(isArrived) System.out.println(1);
        else System.out.println(0);
    }
}