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
    static int N, maxHeight = 0, minHeight = Integer.MAX_VALUE, maxArea = 1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] area, visited;
    static Queue<Location> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        area = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, area[i][j]);
                minHeight = Math.min(minHeight, area[i][j]);
            }
        }

        for(int height = minHeight; height < maxHeight; height++) {
            visited = new int[N][N];
            queue = new LinkedList<Location>();

            int count = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(height < area[i][j] && visited[i][j] == 0) {
                        queue.offer(new Location(i, j));
                        visited[i][j] = 1;

                        while(!queue.isEmpty()) {
                            Location current = queue.poll();

                            int x, y;
                            for(int d = 0; d < 4; d++) {
                                x = current.x + dx[d];
                                y = current.y + dy[d];

                                if(x < 0 || y < 0 || N <= x || N <= y || visited[x][y] == 1 || area[x][y] <= height) continue;
                                
                                visited[x][y] = 1;
                                queue.offer(new Location(x, y));
                            }

                        }

                        count++;
                    }

                }
            }

            maxArea = Math.max(maxArea, count);
        }

        System.out.println(maxArea);
    }
}