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
    static int N, minDistance = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int count = 1;
        Queue<Location> queue = new LinkedList<>();
        Queue<Location> candidates = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0 || visited[i][j]) continue;

                map[i][j] = count;
                visited[i][j] = true;
                queue.offer(new Location(i, j));

                boolean isEdge = false;
                while(!queue.isEmpty()) {
                    Location current = queue.poll();

                    int x, y;
                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];

                        if(x < 0 || y < 0 || N == x || N == y || visited[x][y]) continue;
                        if(map[x][y] == 0) {                                
                            isEdge = true;
                            continue;
                        }

                        map[x][y] = count;
                        visited[x][y] = true;
                        queue.offer(new Location(x,y));
                    }
                    if(isEdge) candidates.offer(current);
                }
                count++;
            }
        }

        while(!candidates.isEmpty()) {
            Location candidate = candidates.poll();

            for(int i = 0; i < N; i++) Arrays.fill(visited[i], false);
            visited[candidate.x][candidate.y] = true;
            queue.clear();
            queue.offer(new Location(candidate.x, candidate.y));
            count = 0;
            loop: while(!queue.isEmpty()) {
                int size = queue.size();
                for(int s = 0; s < size; s++) {
                    Location current = queue.poll();
                    for(int d = 0; d < 4; d++) {
                        int  x = current.x + dx[d], y = current.y + dy[d];
                        if(x < 0 || y < 0 || N == x || N == y || visited[x][y] || map[x][y] == map[candidate.x][candidate.y]) continue;
                        if(map[x][y] != 0) {
                            minDistance = Math.min(minDistance, count);
                            break loop;
                        }
    
                        visited[x][y] = true;
                        queue.offer(new Location(x, y));
                    }
                }
                count++;
                if (minDistance == count) break;
            }
        }
        System.out.println(minDistance);
    }
}