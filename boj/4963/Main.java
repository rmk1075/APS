import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int w, h;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, 1, -1, -1, 1};
    static int[][] map, visited;

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        w = sc.nextInt();
        h = sc.nextInt();

        while(w != 0 || h != 0 ) {
            map = new int[h][w];
            visited = new int[h][w];

            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int ans = 0;
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(map[i][j] == 1 && visited[i][j] == 0) {

                        Queue<Location> queue = new LinkedList<Location>();
                        queue.offer(new Location(i, j));

                        while(!queue.isEmpty()) {
                            Location current = queue.poll();

                            for(int d = 0; d < 8; d++) {
                                int x = current.x + dx[d];
                                int y = current.y + dy[d];

                                if(x < 0 || y < 0 || h <= x || w <= y || visited[x][y] == 1 || map[x][y] == 0) continue;

                                visited[x][y] = 1;
                                queue.offer(new Location(x, y));
                            }
                        }

                        ans++;
                    }
                }
            }

            System.out.println(ans);

            w = sc.nextInt();
            h = sc.nextInt();
        }
    
        sc.close();

    }
}