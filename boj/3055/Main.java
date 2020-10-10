import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int R, C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] visited;
    static Queue<Location> water = new LinkedList<>();
    static Queue<Location> kaktus = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();

            for(int j = 0; j < C; j++) {
                if(map[i][j] == '*') {
                    water.add(new Location(i, j));
                } else if(map[i][j] == 'S') {
                    kaktus.add(new Location(i, j));
                    visited[i][j] = true;
                }
            }
        }
        
        // cal
        int time = 1;
        boolean arrived = false;
        loop:
        while(!kaktus.isEmpty()) {
            // kaktus move
            int size = kaktus.size(), x, y;
            Location current;
            for(int s = 0; s < size; s++) {
                current = kaktus.poll();

                // already die
                if(map[current.x][current.y] == '*') continue;
                
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];
                    
                    if(x < 0 || y < 0 || R == x || C == y || map[x][y] == 'X' || map[x][y] == '*' || visited[x][y]) continue;
                    
                    // if finish
                    if(map[x][y] == 'D') {
                        arrived = true;
                        break loop;
                    }
    
                    visited[x][y] = true;
                    kaktus.offer(new Location(x, y));
                }
            }

            // water fallen
            size = water.size();
            for(int s = 0; s < size; s++) {
                current  = water.poll();

                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];
    
                    if(x < 0 || y < 0 || R == x || C == y || map[x][y] == '*' || map[x][y] == 'X' || map[x][y] == 'D') continue;
    
                    map[x][y] = '*';
                    water.offer(new Location(x, y));
                }
            }
            
            time++;
        }

        // check success of not
        if(arrived) System.out.println(time);
        else System.out.println("KAKTUS");
    }
}