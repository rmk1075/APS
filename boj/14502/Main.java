import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
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
    static int N, M, maxVal;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        maxVal = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        wall(0);

        System.out.println(maxVal);
    }

    public static void wall(int count) {
        if(count == 3) {
            // cal safety area
            Queue<Location> queue = new LinkedList<Location>();
            int[][] tempMap = new int[N][M];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    tempMap[i][j] = map[i][j];

                    if(tempMap[i][j] == 2) queue.offer(new Location(i, j));
                }
            }
            
            while(!queue.isEmpty()) {
                Location current = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int x = current.x + dx[i];
                    int y = current.y + dy[i];

                    if(x < 0 || y < 0 || N <= x || M <= y || tempMap[x][y] != 0) continue;

                    tempMap[x][y] = 2;
                    queue.offer(new Location(x, y));
                }
            }

            int safe = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(tempMap[i][j] == 0) safe++;
                }
            }

            maxVal = Math.max(maxVal, safe);

            return ;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 && visited[i][j] == 0) {
                    map[i][j] = 1;
                    visited[i][j] = 1;
                    wall(count + 1);
                    map[i][j] = 0;
                    visited[i][j] = 0;
                }
            }
        }
    }
}