import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, x, y, shark = 2, ate = 0, count = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] map, visited;
    static Queue<Location> queue = new LinkedList<Location>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    x = i;
                    y = j;
                }
            }
        }

        while(true) {
            visited = new int[N][N];
            queue.clear();
            queue.offer(new Location(x, y));
            visited[x][y] = 1;
            int feedX = N, feedY = N, cnt = 1;
            while(!queue.isEmpty()) {

                boolean isFeed = false;
                int size = queue.size();
                for(int s = 0; s < size; s++) {
                    Location current = queue.poll();
    
                    int x_, y_;
                    for(int i = 0; i < 4; i++) {
                        x_ = current.x + dx[i];
                        y_ = current.y + dy[i];
    
                        if(x_ < 0 || y_ < 0 || N <= x_ || N <= y_ || feedX < x_ || shark < map[x_][y_] || visited[x_][y_] == 1) continue;
    
                        if(map[x_][y_] < shark && map[x_][y_] != 0) {
                        
                            if(x_ < feedX) {
                                feedX = x_;
                                feedY = y_;                                
                            } else if(x_ == feedX && y_ < feedY) {
                                feedX = x_;
                                feedY = y_;
                            }

                            isFeed = true;
                        }
    
                        if(isFeed) continue;

                        visited[x_][y_] = 1;
                        queue.offer(new Location(x_, y_));
                    }

                }

                if(feedX != N) {
                    ate++;
                    if(shark == ate) {
                        ate = 0;
                        shark++;
                    }

                    map[x][y] = 0;
                    map[feedX][feedY] = 9;

                    x = feedX;
                    y = feedY;

                    count += cnt;

                    break;
                }

                cnt++;
            }

            if(feedX == N) break;
        }

        System.out.println(count);
    }
}