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
    static int W, H;
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
    static int[][] map, visited;
    static Queue<Location> queue = new LinkedList<Location>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        while(W != 0 && H != 0) {
            map = new int[H][W];
            visited = new int[H][W];
            
            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int x, y, count = 0;
            for(int i = 0; i < H; i++) {
                for(int j = 0; j < W; j++) {
                    if(map[i][j] == 1 && visited[i][j] == 0) {
                        count++;
                        visited[i][j] = count;
                        queue.offer(new Location(i, j));
                        
                        while(!queue.isEmpty()) {
                            Location current = queue.poll();
                            
                            for(int d = 0; d < 8; d++) {
                                x = current.x + dx[d];
                                y = current.y + dy[d];
                                
                                if(x < 0 || y < 0 || H <= x || W <= y || visited[x][y] != 0 || map[x][y] == 0) continue;
                                
                                visited[x][y] = count;
                                queue.offer(new Location(x, y));
                            }
                        }
                    }
                }
            }

            sb.append(count + "\n");

            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
        }

        System.out.print(sb.toString());
    }
}